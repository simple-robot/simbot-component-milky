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

package love.forte.simbot.milky.model.event.data.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.event.MilkyEventModelConstructor
import love.forte.simbot.milky.model.event.data.MilkyRawEventData
import love.forte.simbot.milky.model.event.data.MilkyRawEventDataMarker

/**
 * [message_recall 消息撤回事件](https://milky.ntqqrev.org/struct/Event#type-message_recall).
 *
 * @see love.forte.simbot.milky.model.event.MilkyRawEvent
 */
@ConsistentCopyVisibility
@Serializable
@SerialName(MilkyRawMessageRecallEventData.SERIAL_NAME)
@MilkyRawEventDataMarker(eventType = MilkyRawMessageRecallEventData.EVENT_TYPE)
public data class MilkyRawMessageRecallEventData
@MilkyEventModelConstructor
internal constructor(
    /**
     * 消息场景
     */
    @SerialName("message_scene")
    public val messageScene: MilkyRawMessageScene,
    /**
     * 好友 QQ 号或群号
     */
    @SerialName("peer_id")
    public val peerId: Long,
    /**
     * 消息序列号
     */
    @SerialName("message_seq")
    public val messageSeq: Long,
    /**
     * 被撤回的消息的发送者 QQ 号
     */
    @SerialName("sender_id")
    public val senderId: Long,
    /**
     * 操作者 QQ 号
     */
    @SerialName("operator_id")
    public val operatorId: Long,
    /**
     * 撤回提示的后缀文本
     */
    @SerialName("display_suffix")
    public val displaySuffix: String,
) : MilkyRawEventData() {
    public companion object {
        public const val EVENT_TYPE: String = "message_recall"
        public const val SERIAL_NAME: String = SERIAL_NAME_PREFIX + EVENT_TYPE
    }
}
