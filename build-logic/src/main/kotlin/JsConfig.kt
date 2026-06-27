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

import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsNodeDsl
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsTargetDsl
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinWasmJsTargetDsl

inline fun KotlinJsTargetDsl.configJs(
    crossinline configNodejs: KotlinJsNodeDsl.() -> Unit = {},
    block: () -> Unit = {}
) {
    nodejs {
        testTask {
            useMocha {
                timeout = "30s"
            }
        }
        configNodejs()
    }

    binaries.library()
    block()
}

inline fun KotlinWasmJsTargetDsl.configWasmJs(
    crossinline configNodejs: KotlinJsNodeDsl.() -> Unit = {},
    block: () -> Unit = {}
) {
    nodejs {
        configNodejs()
    }

    binaries.library()
    block()
}
