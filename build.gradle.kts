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

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

plugins {
    idea
    id("org.jetbrains.dokka")
}

group = P.ComponentMilky.group
version = P.ComponentMilky.version
description = P.ComponentMilky.description

repositories {
    mavenCentral()
}

allprojects {
    group = P.ComponentMilky.group
    version = P.ComponentMilky.version
    description = P.ComponentMilky.description
}

subprojects {
    repositories {
        mavenCentral()
    }
}

dependencies {
    dokka(project(":simbot-component-milky-model"))
    dokka(project(":simbot-component-milky-api"))
    dokka(project(":simbot-component-milky-core"))
}

dokka {
    moduleName = P.ComponentMilky.dokkaModuleName
    dokkaPublications.all {
        if (isSimbotLocal()) {
            offlineMode = true
        }
    }

    configSourceSets(project)

    pluginsConfiguration.html {
        configHtmlCustoms(project)
    }
}

tasks.register("createChangelog") {
    group = "documentation"
    description = "Create a lightweight changelog file for the current version tag."

    val outputDir = layout.projectDirectory.dir(".changelog")
    outputs.dir(outputDir)

    doLast {
        val versionTag = "v${project.version}"
        val file = outputDir.file("$versionTag.md").asFile
        file.parentFile.mkdirs()
        file.writeText(
            """
            # $versionTag
            
            Generated at ${
                OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            }.
            
            - Repository: ${P.ComponentMilky.homepage}
            - Version: ${project.version}
            """.trimIndent() + "\n"
        )
    }
}
