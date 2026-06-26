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

package love.forte.simbot.milky.model.event.data.group

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.event.MilkyEventModelConstructor
import love.forte.simbot.milky.model.event.data.MilkyRawEventData
import love.forte.simbot.milky.model.event.data.MilkyRawEventDataMarker

/**
 * [group_message_reaction 群消息表情回应事件](https://milky.ntqqrev.org/struct/Event#type-group_message_reaction).
 *
 * @see love.forte.simbot.milky.model.event.MilkyRawEvent
 */
@Serializable
@SerialName(MilkyRawGroupMessageReactionEventData.SERIAL_NAME)
@MilkyRawEventDataMarker(eventType = MilkyRawGroupMessageReactionEventData.EVENT_TYPE)
public data class MilkyRawGroupMessageReactionEventData
@MilkyEventModelConstructor
internal constructor(
    /**
     * 群号
     */
    @SerialName("group_id")
    public val groupId: Long,
    /**
     * 发送回应者 QQ 号
     */
    @SerialName("user_id")
    public val userId: Long,
    /**
     * 消息序列号
     */
    @SerialName("message_seq")
    public val messageSeq: Long,
    /**
     * 表情 ID
     */
    @SerialName("face_id")
    public val faceId: String,
    /**
     * 收到的回应类型
     */
    @SerialName("reaction_type")
    public val reactionType: MilkyRawGroupMessageReactionType,
    /**
     * 是否为添加，false 表示取消回应
     */
    @SerialName("is_add")
    public val isAdd: Boolean,
) : MilkyRawEventData() {
    public companion object {
        public const val EVENT_TYPE: String = "group_message_reaction"
        public const val SERIAL_NAME: String = SERIAL_NAME_PREFIX + EVENT_TYPE
    }
}
