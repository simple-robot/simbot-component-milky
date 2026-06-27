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

import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmDefaultMode
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget

inline fun KotlinJvmTarget.configJava(crossinline block: KotlinJvmTarget.() -> Unit = {}) {
    compilerOptions {
        javaParameters.set(true)
        freeCompilerArgs.add("-Xjsr305=strict")
        jvmDefault.set(JvmDefaultMode.NO_COMPATIBILITY)
    }

    project.tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        reports.html.outputLocation.set(
            project.rootProject.layout.buildDirectory.dir("test-reports/html/${project.name}")
        )
    }

    block()
}

fun KotlinBaseExtension.configJavaToolchain(jdkVersion: Int) {
    jvmToolchain(jdkVersion)
}

inline fun KotlinMultiplatformExtension.configKotlinJvm(
    jdkVersion: Int = JVMConstants.KT_JVM_TARGET_VALUE,
    crossinline block: KotlinJvmTarget.() -> Unit = {}
) {
    configJavaToolchain(jdkVersion)
    jvm {
        configJava(block)
    }
}

inline fun KotlinJvmProjectExtension.configKotlinJvm(
    jdkVersion: Int = JVMConstants.KT_JVM_TARGET_VALUE,
    crossinline block: KotlinJvmProjectExtension.() -> Unit = {}
) {
    configJavaToolchain(jdkVersion)
    compilerOptions {
        javaParameters.set(true)
        jvmTarget.set(JvmTarget.fromTarget(jdkVersion.toString()))
        freeCompilerArgs.add("-Xjsr305=strict")
    }
    block()
}
