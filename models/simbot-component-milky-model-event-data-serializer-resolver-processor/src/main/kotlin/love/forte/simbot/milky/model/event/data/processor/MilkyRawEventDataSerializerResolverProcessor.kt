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
import love.forte.codegentle.kotlin.spec.KotlinObjectTypeSpec
import love.forte.codegentle.kotlin.spec.addFunction
import love.forte.codegentle.kotlin.spec.addParameter
import love.forte.codegentle.kotlin.writeToKotlinString

/**
 * Generates `resolveMilkyRawEventDataSerializer(eventType: String)` for
 * classes annotated with `MilkyRawEventDataMarker`.
 */
public class MilkyRawEventDataSerializerResolverProcessor(
    environment: SymbolProcessorEnvironment,
) : SymbolProcessor {
    private val codeGenerator: CodeGenerator = environment.codeGenerator
    private val logger: KSPLogger = environment.logger

    private val entries = mutableListOf<EventDataEntry>()

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val deferred = mutableListOf<KSAnnotated>()
        val symbols = mutableListOf<KSAnnotated>()

        resolver.getSymbolsWithAnnotation(EVENT_DATA_MARKER_FQ_NAME)
            .forEach { symbol ->
                if (symbol.validate()) {
                    symbols.add(symbol)
                } else {
                    deferred.add(symbol)
                }
            }

        appendEntries(symbols)
        return deferred
    }

    override fun finish() {
        generateResolver(entries)
    }

    private fun appendEntries(symbols: List<KSAnnotated>) {
        for (symbol in symbols) {
            val declaration = symbol as? KSClassDeclaration ?: run {
                logger.error(
                    "@MilkyRawEventDataMarker can only be applied to classes, but was ${symbol::class.qualifiedName}.",
                    symbol
                )
                continue
            }

            requireNotNull(declaration.findAnnotation(K_SERIALIZABLE_ANNOTATION_FQ_NAME)) {
                "Class ${declaration.simpleName} is missing @Serializable annotation."
            }

            val annotation = declaration.findAnnotation(EVENT_DATA_MARKER_FQ_NAME) ?: run {
                logger.error("Cannot resolve @MilkyRawEventDataMarker from $declaration.", declaration)
                continue
            }

            val eventType = annotation.arguments
                .firstOrNull { it.name?.asString() == EVENT_TYPE_ARGUMENT_NAME }
                ?.value as? String
                ?: run {
                    logger.error(
                        "Missing annotation argument '$EVENT_TYPE_ARGUMENT_NAME' on $declaration.",
                        declaration
                    )
                    continue
                }

            entries += EventDataEntry(
                eventType = eventType,
                declaration = declaration.toClassName(),
                containingFile = declaration.containingFile,
            )
        }
    }

    @OptIn(AnnotationTargetExtensionScope::class)
    private fun generateResolver(entries: List<EventDataEntry>) {
        val resolverObject = KotlinObjectTypeSpec(GENERATED_OBJECT_NAME) {
            addModifier(KotlinModifier.PUBLIC)
            addDoc(
                """
                根据 `event_type` 的值解析得到一个对应的 [%V] 的序列化器，或在获取不到的时候得到 `null`。
                注意：鉴于 `incoming_message` 有特殊的子结构规则，
                因此解析结果 **不包括** `event_type` = `incoming_message` 的结果。
            """.trimIndent()
            ) {
                emitType(MILKY_RAW_EVENT_DATA)
            }
            addFunction(SIMPLE_RESOLVE_FUNCTION_NAME, serializerResolverReturnType()) {
                addModifier(KotlinModifier.PUBLIC)
                addParameter("eventType", STRING.ref())
                addCode {
                    buildResolverCode(entries)
                }
            }
        }

        val file = KotlinFile(TARGET_PACKAGE.parseToPackageName(), resolverObject) {
            name(resolverObject.name)
        }

        val dependencies = Dependencies(
            true,
            *entries.mapNotNull { it.containingFile }.distinct().toTypedArray()
        )

        codeGenerator
            .createNewFile(dependencies, TARGET_PACKAGE, GENERATED_OBJECT_NAME)
            .writer()
            .use { writer ->
                writer.write(file.writeToKotlinString())
            }
    }

    private fun serializerResolverReturnType() =
        K_SERIALIZER
            .parameterized(LowerWildcardTypeName(MILKY_RAW_EVENT_DATA.ref()).ref())
            .ref {
                kotlinStatus {
                    nullable = true
                }
            }

    private fun CodeValueBuilder.buildResolverCode(entries: List<EventDataEntry>) {
        addCode("return ")
        beginControlFlow("when (eventType)")
        entries.forEach { entry ->
            if (entry.eventType == SPECIAL_MESSAGE_EVENT_TYPE) {
                logger.warn("Skipping special event type '$SPECIAL_MESSAGE_EVENT_TYPE' for resolver generation.")
                return@forEach
            }

            addStatement("%V -> %V.serializer()") {
                emitString(entry.eventType)
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

    private data class EventDataEntry(
        val eventType: String,
        val declaration: ClassName,
        val containingFile: KSFile?,
    )

    private companion object {
        private const val SPECIAL_MESSAGE_EVENT_TYPE = "incoming_message"
        private const val TARGET_PACKAGE: String = "love.forte.simbot.milky.model.event.data"
        private const val GENERATED_OBJECT_NAME: String = "MilkyRawEventDataSerializerResolver"
        private const val SIMPLE_RESOLVE_FUNCTION_NAME: String = "resolveMilkyRawEventDataSerializer"
        private const val EVENT_TYPE_ARGUMENT_NAME: String = "eventType"
        private const val EVENT_DATA_MARKER_FQ_NAME: String =
            "love.forte.simbot.milky.model.event.data.MilkyRawEventDataMarker"

        private val STRING: ClassName = KotlinClassNames.STRING
        private val K_SERIALIZABLE_ANNOTATION_FQ_NAME: String =
            "kotlinx.serialization.Serializable"

        private val K_SERIALIZER: ClassName = ClassName("kotlinx.serialization", "KSerializer")
        private val MILKY_RAW_EVENT_DATA: ClassName =
            ClassName("love.forte.simbot.milky.model.event.data", "MilkyRawEventData")
    }
}
