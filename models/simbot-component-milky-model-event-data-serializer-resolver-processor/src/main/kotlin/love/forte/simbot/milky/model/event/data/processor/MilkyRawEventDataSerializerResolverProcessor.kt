/*
 *     Copyright (c) 2026. ForteScarlet.
 *
 *     Project    https://github.com/simple-robot/simbot-component-milky
 *     Email      ForteScarlet@163.com
 *
 *     This file is part of simbot-component-milky.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     Lesser GNU General Public License for more details.
 *
 *     You should have received a copy of the Lesser General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package love.forte.simbot.milky.model.event.data.processor

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.validate
import love.forte.codegentle.common.code.*
import love.forte.codegentle.common.ksp.toClassName
import love.forte.codegentle.common.naming.ClassName
import love.forte.codegentle.common.naming.LowerWildcardTypeName
import love.forte.codegentle.common.naming.parameterized
import love.forte.codegentle.common.naming.parseToPackageName
import love.forte.codegentle.common.ref.ref
import love.forte.codegentle.kotlin.KotlinFile
import love.forte.codegentle.kotlin.KotlinModifier
import love.forte.codegentle.kotlin.naming.AnnotationTargetExtensionScope
import love.forte.codegentle.kotlin.naming.KotlinClassNames
import love.forte.codegentle.kotlin.ref.kotlinStatus
import love.forte.codegentle.kotlin.spec.*
import love.forte.codegentle.kotlin.writeToKotlinString

/**
 * Generates serializer resolvers for event data and incoming message segment models.
 */
public class MilkyRawEventDataSerializerResolverProcessor(
    environment: SymbolProcessorEnvironment,
) : SymbolProcessor {
    private val codeGenerator: CodeGenerator = environment.codeGenerator
    private val logger: KSPLogger = environment.logger

    private val eventDataEntries = mutableListOf<SerializerResolverEntry>()
    private val incomingSegmentEntries = mutableListOf<SerializerResolverEntry>()

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val deferred = mutableListOf<KSAnnotated>()

        appendEntries(resolver, EVENT_DATA_RESOLVER_SPEC, eventDataEntries, deferred)
        appendEntries(resolver, INCOMING_SEGMENT_RESOLVER_SPEC, incomingSegmentEntries, deferred)
        return deferred
    }

    override fun finish() {
        generateResolver(EVENT_DATA_RESOLVER_SPEC, eventDataEntries)
        generateResolver(INCOMING_SEGMENT_RESOLVER_SPEC, incomingSegmentEntries)
    }

    private fun appendEntries(
        resolver: Resolver,
        spec: SerializerResolverSpec,
        entries: MutableList<SerializerResolverEntry>,
        deferred: MutableList<KSAnnotated>,
    ) {
        val symbols = mutableListOf<KSAnnotated>()

        resolver.getSymbolsWithAnnotation(spec.markerAnnotationName)
            .forEach { symbol ->
                if (symbol.validate()) {
                    symbols.add(symbol)
                } else {
                    deferred.add(symbol)
                }
            }

        for (symbol in symbols) {
            val declaration = symbol as? KSClassDeclaration ?: run {
                logger.error(
                    "@${spec.markerAnnotationSimpleName} can only be applied to classes, " +
                        "but was ${symbol::class.qualifiedName}.",
                    symbol
                )
                continue
            }

            requireNotNull(declaration.findAnnotation(K_SERIALIZABLE_ANNOTATION_FQ_NAME)) {
                "Class ${declaration.simpleName} is missing @Serializable annotation."
            }

            val annotation = declaration.findAnnotation(spec.markerAnnotationName) ?: run {
                logger.error("Cannot resolve @${spec.markerAnnotationSimpleName} from $declaration.", declaration)
                continue
            }

            val discriminatorValue = annotation.arguments
                .firstOrNull { it.name?.asString() == spec.markerArgumentName }
                ?.value as? String
                ?: run {
                    logger.error(
                        "Missing annotation argument '${spec.markerArgumentName}' on $declaration.",
                        declaration
                    )
                    continue
                }

            entries += SerializerResolverEntry(
                discriminatorValue = discriminatorValue,
                declaration = declaration.toClassName(),
                containingFile = declaration.containingFile,
            )
        }
    }

    @OptIn(AnnotationTargetExtensionScope::class)
    private fun generateResolver(
        spec: SerializerResolverSpec,
        entries: List<SerializerResolverEntry>,
    ) {
        val effectiveEntries = effectiveEntries(spec, entries)
        val resolverObject = KotlinObjectTypeSpec(spec.generatedObjectName) {
            addModifier(KotlinModifier.PUBLIC)
            addDoc(spec.generatedObjectDoc) {
                emitType(spec.baseClassName)
            }
            addProperty(buildSerializersMapProperty(spec, effectiveEntries))
            addFunction(spec.resolveFunctionName, serializerResolverReturnType(spec.baseClassName)) {
                addModifier(KotlinModifier.PUBLIC)
                addParameter(spec.resolveParameterName, STRING.ref())
                addCode {
                    buildResolverCode(spec, effectiveEntries)
                }
            }
        }

        val file = KotlinFile(spec.targetPackage.parseToPackageName(), resolverObject) {
            name(resolverObject.name)
        }

        val dependencies = Dependencies(
            true,
            *entries.mapNotNull { it.containingFile }.distinct().toTypedArray()
        )

        codeGenerator
            .createNewFile(dependencies, spec.targetPackage, spec.generatedObjectName)
            .writer()
            .use { writer ->
                writer.write(file.writeToKotlinString())
            }
    }

    private fun buildSerializersMapProperty(
        spec: SerializerResolverSpec,
        entries: List<SerializerResolverEntry>,
    ) = KotlinPropertySpec(spec.mapPropertyName, serializersMapType(spec.baseClassName)) {
        addModifier(KotlinModifier.PUBLIC)
        addDoc(spec.mapPropertyDoc) {
            emitType(spec.baseClassName)
        }
        initializer(buildSerializersMapInitializer(entries)) {
            entries.forEach { entry ->
                emitString(entry.discriminatorValue)
                emitType(entry.declaration)
            }
        }
    }

    private fun buildSerializersMapInitializer(entries: List<SerializerResolverEntry>): String {
        if (entries.isEmpty()) {
            return "emptyMap()"
        }

        return buildString {
            appendLine("mapOf(")
            entries.forEach {
                appendLine("    %V to %V.serializer(),")
            }
            append(")")
        }
    }

    private fun effectiveEntries(
        spec: SerializerResolverSpec,
        entries: List<SerializerResolverEntry>,
    ): List<SerializerResolverEntry> {
        return entries
            .filter { entry ->
                val skip = entry.discriminatorValue in spec.skippedDiscriminatorValues
                if (skip) {
                    logger.warn("Skipping special discriminator value '${entry.discriminatorValue}' for resolver generation.")
                }
                !skip
            }
            .sortedBy { it.discriminatorValue }
    }

    private fun serializersMapType(baseClassName: ClassName) =
        MAP
            .parameterized(
                STRING.ref(),
                K_SERIALIZER
                    .parameterized(LowerWildcardTypeName(baseClassName.ref()).ref())
                    .ref()
            )
            .ref()

    private fun serializerResolverReturnType(baseClassName: ClassName) =
        K_SERIALIZER
            .parameterized(LowerWildcardTypeName(baseClassName.ref()).ref())
            .ref {
                kotlinStatus {
                    nullable = true
                }
            }

    private fun CodeValueBuilder.buildResolverCode(
        spec: SerializerResolverSpec,
        entries: List<SerializerResolverEntry>,
    ) {
        addCode("return ")
        beginControlFlow("when (${spec.resolveParameterName})")
        effectiveEntries(spec, entries).forEach { entry ->
            addStatement("%V -> %V.serializer()") {
                emitString(entry.discriminatorValue)
                emitType(entry.declaration)
            }
        }
        addStatement("else -> null")
        endControlFlow()
    }

    private fun KSClassDeclaration.findAnnotation(annotationQualifiedName: String): KSAnnotation? =
        annotations.firstOrNull { annotation ->
            annotation.annotationType.resolve().declaration.qualifiedName?.asString() == annotationQualifiedName
        }

    private data class SerializerResolverEntry(
        val discriminatorValue: String,
        val declaration: ClassName,
        val containingFile: KSFile?,
    )

    private data class SerializerResolverSpec(
        val markerAnnotationName: String,
        val markerAnnotationSimpleName: String,
        val markerArgumentName: String,
        val targetPackage: String,
        val generatedObjectName: String,
        val mapPropertyName: String,
        val resolveFunctionName: String,
        val resolveParameterName: String,
        val generatedObjectDoc: String,
        val mapPropertyDoc: String,
        val baseClassName: ClassName,
        val skippedDiscriminatorValues: Set<String> = emptySet(),
    )

    private companion object {
        private const val SPECIAL_MESSAGE_EVENT_TYPE = "incoming_message"

        private val STRING: ClassName = KotlinClassNames.STRING
        private val MAP: ClassName = ClassName("kotlin.collections", "Map")
        private const val K_SERIALIZABLE_ANNOTATION_FQ_NAME: String =
            "kotlinx.serialization.Serializable"

        private val K_SERIALIZER: ClassName = ClassName("kotlinx.serialization", "KSerializer")
        private val MILKY_RAW_EVENT_DATA: ClassName =
            ClassName("love.forte.simbot.milky.model.event.data", "MilkyRawEventData")
        private val MILKY_RAW_INCOMING_MESSAGE_SEGMENT: ClassName =
            ClassName(
                "love.forte.simbot.milky.model.event.data.message.segment.incoming",
                "MilkyRawIncomingMessageSegment"
            )

        private val EVENT_DATA_RESOLVER_SPEC = SerializerResolverSpec(
            markerAnnotationName = "love.forte.simbot.milky.model.event.data.MilkyRawEventDataMarker",
            markerAnnotationSimpleName = "MilkyRawEventDataMarker",
            markerArgumentName = "eventType",
            targetPackage = "love.forte.simbot.milky.model.event.data",
            generatedObjectName = "MilkyRawEventDataSerializerResolver",
            mapPropertyName = "milkyRawEventDataSerializersMap",
            resolveFunctionName = "resolveMilkyRawEventDataSerializer",
            resolveParameterName = "eventType",
            generatedObjectDoc = """
                根据 `event_type` 的值解析得到一个对应的 [%V] 的序列化器，或在获取不到的时候得到 `null`。
                注意：鉴于 `incoming_message` 有特殊的子结构规则，
                因此解析结果 **不包括** `event_type` = `incoming_message` 的结果。
            """.trimIndent(),
            mapPropertyDoc = """
                以 `event_type` 为 key 的 [%V] 序列化器映射表。
                注意：鉴于 `incoming_message` 有特殊的子结构规则，
                因此映射结果 **不包括** `event_type` = `incoming_message` 的结果。
            """.trimIndent(),
            baseClassName = MILKY_RAW_EVENT_DATA,
            skippedDiscriminatorValues = setOf(SPECIAL_MESSAGE_EVENT_TYPE),
        )

        private val INCOMING_SEGMENT_RESOLVER_SPEC = SerializerResolverSpec(
            markerAnnotationName =
                "love.forte.simbot.milky.model.event.data.message.segment.incoming." +
                    "MilkyRawIncomingMessageSegmentTypeMarker",
            markerAnnotationSimpleName = "MilkyRawIncomingMessageSegmentTypeMarker",
            markerArgumentName = "type",
            targetPackage = "love.forte.simbot.milky.model.event.data.message.segment.incoming",
            generatedObjectName = "MilkyRawIncomingMessageSegmentSerializerResolver",
            mapPropertyName = "milkyRawIncomingMessageSegmentSerializersMap",
            resolveFunctionName = "resolveMilkyRawIncomingMessageSegmentSerializer",
            resolveParameterName = "type",
            generatedObjectDoc = """
                根据 `type` 的值解析得到一个对应的 [%V] 的序列化器，或在获取不到的时候得到 `null`。
            """.trimIndent(),
            mapPropertyDoc = """
                以 `type` 为 key 的 [%V] 序列化器映射表。
            """.trimIndent(),
            baseClassName = MILKY_RAW_INCOMING_MESSAGE_SEGMENT,
        )
    }
}
