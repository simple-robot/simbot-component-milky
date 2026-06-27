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

import org.jetbrains.kotlin.gradle.dsl.KotlinTargetContainerWithPresetFunctions
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

fun KotlinTargetContainerWithPresetFunctions.applyTier1(): List<KotlinNativeTarget> {
    return buildList {
        add(macosArm64())
        add(iosSimulatorArm64())
        add(iosArm64())
    }
}

fun KotlinTargetContainerWithPresetFunctions.applyTier2(): List<KotlinNativeTarget> {
    return buildList {
        add(linuxX64())
        add(watchosSimulatorArm64())
        add(watchosArm32())
        add(watchosArm64())
        add(tvosSimulatorArm64())
        add(tvosArm64())
    }
}

fun KotlinTargetContainerWithPresetFunctions.applyTier3(
    watchosX64: Boolean = true,
    watchosDeviceArm64: Boolean = false,
    androidNative: Boolean = false,
): List<KotlinNativeTarget> {
    return buildList {
        if (androidNative) {
            add(androidNativeArm32())
            add(androidNativeArm64())
            add(androidNativeX86())
            add(androidNativeX64())
        }
        add(mingwX64())
        if (watchosDeviceArm64) {
            add(watchosDeviceArm64())
        }
        add(macosX64())
        add(iosX64())
        if (watchosX64) {
            add(watchosX64())
        }
        add(tvosX64())
    }
}

fun KotlinTargetContainerWithPresetFunctions.applyTier123(): List<KotlinNativeTarget> {
    return buildList {
        addAll(applyTier1())
        addAll(applyTier2())
        addAll(applyTier3())
    }
}
