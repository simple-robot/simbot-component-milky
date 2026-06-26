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
 *     You should have received a copy of the Lesser General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package love.forte.simbot.milky.model.event.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.event.MilkyEventModelConstructor
import love.forte.simbot.milky.model.event.data.message.MilkyRawMessageScene

/**
 * [peer_pin_change 会话置顶变更事件](https://milky.ntqqrev.org/struct/Event#type-peer_pin_change).
 *
 * @see love.forte.simbot.milky.model.event.MilkyRawEvent
 */
@Serializable
@SerialName(MilkyRawPeerPinChangeEventData.SERIAL_NAME)
@MilkyRawEventDataMarker(eventType = MilkyRawPeerPinChangeEventData.EVENT_TYPE)
public data class MilkyRawPeerPinChangeEventData
@MilkyEventModelConstructor
internal constructor(
    /**
     * 会话消息场景
     */
    @SerialName("message_scene")
    public val messageScene: MilkyRawMessageScene,
    /**
     * 好友 QQ 号或群号
     */
    @SerialName("peer_id")
    public val peerId: Long,
    /**
     * 是否被置顶，false 表示取消置顶
     */
    @SerialName("is_pinned")
    public val isPinned: Boolean,
) : MilkyRawEventData() {
    public companion object {
        public const val EVENT_TYPE: String = "peer_pin_change"
        public const val SERIAL_NAME: String = SERIAL_NAME_PREFIX + EVENT_TYPE
    }
}
