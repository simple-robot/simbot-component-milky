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

plugins {
    idea
    id("org.jetbrains.dokka")
}

allprojects {
    group = P.ComponentMilky.GROUP
    version = P.ComponentMilky.version
    description = P.ComponentMilky.DESCRIPTION
}

subprojects {
    afterEvaluate {
        if (plugins.hasPlugin("org.jetbrains.dokka")) {
            rootProject.dependencies.add("dokka", project(path))
        }
    }
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
