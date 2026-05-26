/*
 *     Copyright (c) 2026. ForteScarlet.
 *
 *     Project    https://github.com/simple-robot/simpler-robot
 *     Email      ForteScarlet@163.com
 *
 *     This file is part of the Simple Robot Library (Alias: simple-robot, simbot, etc.).
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
 *     You should have received a copy of the Lesser GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package love.forte.simbot.milky.model.event.data

import kotlinx.serialization.Serializable

/**
 * [love.forte.simbot.milky.model.event.MilkyRawEvent] 的 [love.forte.simbot.milky.model.event.MilkyRawEvent.data] 数据类型的基本父类型。
 *
 * @author Forte Scarlet
 */
@Serializable
public abstract class MilkyRawEventData {
    public companion object {
        public const val SERIAL_NAME_PREFIX: String = "milky_event_data_"
    }
}

/**
 * 用于注解处理器对 [MilkyRawEventData] 序列化器进行解析与处理的标记注解。
 *
 * 会收集所有标记了此注解以及 [@Serializable][Serializable] 的 [MilkyRawEventData] 的子类型，
 * 并产生顶层函数：
 *
 * ```Kotlin
 * fun resolveMilkyRawEventDataSerializer(eventType: String): KSerializer<out MilkyRawEventData>? {
 *     return when (eventType) {
 *          "bot_offline" -> MilkyRawBotOfflineEventData.serializer()
 *          "xxx" -> ...
 *          else -> null
 *     }
 * }
 * ```
 *
 * @see love.forte.simbot.milky.model.event.data.message.MilkyRawIncomingMessageEventData
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
public annotation class MilkyRawEventDataMarker(val eventType: String)
