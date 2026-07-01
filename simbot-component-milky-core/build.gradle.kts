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
    id("milky.module-conventions")
    id("milky.suspend-transform")
}

description = "Core implementation of simbot-component-milky."

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":simbot-component-milky-api"))
            api(libs.simbot.core)
            api(libs.simbot.logger)
            api(libs.simbot.common.annotations)
            api(libs.simbot.common.core)
            api(libs.simbot.common.suspend.runner)
            api(libs.kotlinx.coroutines.core)
            api(libs.kotlinx.io.core)
            api(libs.ktor.client.ws)
        }

        commonTest.dependencies {
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.ktor.client.mock)
        }

        jvmTest.dependencies {
            implementation(libs.ktor.client.java)
            implementation(libs.log4j.api)
            implementation(libs.log4j.core)
            implementation(libs.log4j.slf4j2)
        }

        jvmMain.dependencies {
            compileOnly(libs.kotlinx.coroutines.reactive)
        }

        jsMain.dependencies {
            implementation(libs.ktor.client.js)
        }
    }
}
