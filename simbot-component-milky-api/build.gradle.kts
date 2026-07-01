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

description = "API abstractions and client contracts for simbot-component-milky."

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcollection-literals")
        optIn.add("love.forte.simbot.milky.api.InternalMilkyApiSerializerJson")
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":models:simbot-component-milky-model-api"))
            api(project(":models:simbot-component-milky-model-entity"))
            // api(project(":models:simbot-component-milky-model-event"))
            // api(libs.simbot.api)
            api(libs.simbot.common.annotations)
            api(libs.simbot.common.suspend.runner)
            //api(libs.simbot.common.time)
            // TODO 这个还得完善
            // api(libs.simbot.common.apidefinition)
            api(libs.kotlinx.coroutines.core)
            api(libs.kotlinx.serialization.json)
            api(libs.ktor.client.core)
            // api(libs.ktor.client.contentNegotiation)
            // api(libs.ktor.serialization.kotlinxJson)
        }

        commonTest.dependencies {
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.ktor.client.mock)
        }

        jvmMain.dependencies {
            compileOnly(libs.kotlinx.coroutines.reactive)
        }

        jsMain.dependencies {
            api(libs.ktor.client.js)
        }
    }
}

dokka {
    dokkaSourceSets {
        named("commonMain") {
            samples.from(projectDir.resolve("src/commonTest/kotlin/"))
        }
    }
}
