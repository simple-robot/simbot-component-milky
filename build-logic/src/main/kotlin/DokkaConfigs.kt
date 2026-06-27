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

import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.withType
import org.jetbrains.dokka.gradle.DokkaExtension
import org.jetbrains.dokka.gradle.engine.parameters.DokkaSourceSetSpec
import org.jetbrains.dokka.gradle.engine.parameters.VisibilityModifier
import org.jetbrains.dokka.gradle.engine.plugins.DokkaHtmlPluginParameters
import java.io.File
import java.net.URI
import java.time.Year

fun DokkaExtension.configSourceSets(project: Project) {
    dokkaSourceSets.configureEach {
        skipEmptyPackages.set(true)
        suppressGeneratedFiles.set(false)
        documentedVisibilities(
            VisibilityModifier.Public,
            VisibilityModifier.Protected,
        )

        when (val targetCompatibility = project.tasks.withType<JavaCompile>().firstOrNull()?.targetCompatibility) {
            "1.8" -> jdkVersion.set(8)
            null -> Unit
            else -> jdkVersion.set(targetCompatibility.toInt())
        }

        configModuleMdInclude(project)

        perPackageOption {
            matchingRegex.set(".*internal.*")
            suppress.set(true)
        }

        configSourceLink(project)
        configExternalDocumentations()
    }
}

fun DokkaSourceSetSpec.configModuleMdInclude(project: Project) {
    val moduleFile = project.file("Module.md")
    if (moduleFile.exists() && moduleFile.length() > 0) {
        includes.from(moduleFile)
    }
}

fun DokkaSourceSetSpec.configSourceLink(project: Project) {
    sourceLink {
        localDirectory.set(project.projectDir)
        val relativeTo = project.projectDir.relativeTo(project.rootProject.projectDir).toString().replace('\\', '/')
        remoteUrl.set(URI.create("${P.ComponentMilky.HOMEPAGE}/tree/main/$relativeTo"))
        remoteLineSuffix.set("#L")
    }
}

fun DokkaSourceSetSpec.configExternalDocumentations() {
    fun externalDocumentation(name: String, docUrl: URI, suffix: String = "package-list") {
        externalDocumentationLinks.register(name) {
            url.set(docUrl)
            packageListUrl.set(docUrl.resolve(suffix))
        }
    }

    externalDocumentation("kotlinx.coroutines", URI.create("https://kotlinlang.org/api/kotlinx.coroutines/"))
    externalDocumentation("kotlinx.serialization", URI.create("https://kotlinlang.org/api/kotlinx.serialization/"))
    externalDocumentation("ktor", URI.create("https://api.ktor.io/"))
    externalDocumentation("slf4j", URI.create("https://www.slf4j.org/apidocs/"), "element-list")
}

fun DokkaHtmlPluginParameters.configHtmlCustoms(project: Project) {
    customAssets.from(
        project.rootProject.file(".simbot/dokka-assets/logo-icon.svg"),
        project.rootProject.file(".simbot/dokka-assets/logo-icon-light.svg"),
    )
    customStyleSheets.from(project.rootProject.file(".simbot/dokka-assets/css/kdoc-style.css"))

    if (!isSimbotLocal()) {
        templatesDir.set(project.rootProject.file(".simbot/dokka-templates"))
    }

    val now = Year.now().value
    footerMessage.set(
        "© 2026-$now <a href='https://github.com/simple-robot'>Simple Robot</a>. All rights reserved."
    )
    separateInheritedMembers.set(true)
    mergeImplicitExpectActualDeclarations.set(true)
    homepageLink.set(P.ComponentMilky.HOMEPAGE)
}
