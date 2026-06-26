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

data class DeveloperMeta(
    val id: String,
    val name: String,
    val email: String,
    val url: String,
)

data class LicenseMeta(
    val name: String,
    val url: String,
)

object P {
    object ComponentMilky {
        const val GROUP = "love.forte.simbot.component"
        const val DESCRIPTION = "Simple Robot framework component for the Milky platform."
        const val HOMEPAGE = "https://github.com/simple-robot/simbot-component-milky"
        const val dokkaModuleName = "Simple Robot Component | Milky"
        const val VERSION_NAME = "0.0.1"
        const val NEXT_VERSION_NAME = "0.0.1"

        val version: String
            get() = if (isSnapshot()) {
                "$NEXT_VERSION_NAME-SNAPSHOT"
            } else {
                VERSION_NAME
            }

        val developers = listOf(
            DeveloperMeta(
                id = "forte",
                name = "ForteScarlet",
                email = "ForteScarlet@163.com",
                url = "https://github.com/ForteScarlet",
            ),
            DeveloperMeta(
                id = "forliy",
                name = "ForliyScarlet",
                email = "ForliyScarlet@163.com",
                url = "https://github.com/ForliyScarlet",
            ),
        )

        val licenses = listOf(
            LicenseMeta(
                name = "GNU GENERAL PUBLIC LICENSE, Version 3",
                url = "https://www.gnu.org/licenses/gpl-3.0-standalone.html",
            ),
            LicenseMeta(
                name = "GNU LESSER GENERAL PUBLIC LICENSE, Version 3",
                url = "https://www.gnu.org/licenses/lgpl-3.0-standalone.html",
            ),
        )
    }
}

fun isSnapshot(): Boolean {
    val fromProperty = System.getProperty("isSnapshot")?.toBooleanStrictOrNull() ?: false
    val fromEnv = System.getenv(Env.IS_SNAPSHOT)?.toBooleanStrictOrNull() ?: false
    return fromProperty || fromEnv
}

fun isSimbotLocal(): Boolean = System.getProperty("SIMBOT_LOCAL")?.toBooleanStrictOrNull()
    ?: System.getenv("SIMBOT_LOCAL")?.toBooleanStrictOrNull()
    ?: false
