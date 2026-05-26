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

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.event.MilkyEventModelConstructor

/**
 * [bot_offline 机器人离线事件](https://milky.ntqqrev.org/struct/Event#type-bot_offline).
 *
 * @see love.forte.simbot.milky.model.event.MilkyRawEvent
 */
@ConsistentCopyVisibility
@Serializable
@SerialName(MilkyRawBotOfflineEventData.SERIAL_NAME)
@MilkyRawEventDataMarker(eventType = MilkyRawBotOfflineEventData.EVENT_TYPE)
public data class MilkyRawBotOfflineEventData
@MilkyEventModelConstructor
internal constructor(
    /**
     * 离线原因
     */
    public val reason: String,
) : MilkyRawEventData() {
    public companion object {
        public const val EVENT_TYPE: String = "bot_offline"
        public const val SERIAL_NAME: String = SERIAL_NAME_PREFIX + EVENT_TYPE
    }
}
