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

package love.forte.simbot.milky.model.event.data.message.segment.incoming

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.event.MilkyEventModelConstructor

/**
 * [reply 回复消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-reply).
 */
@Serializable
@SerialName(MilkyRawIncomingReplyMessageSegment.TYPE)
@MilkyRawIncomingMessageSegmentTypeMarker(MilkyRawIncomingReplyMessageSegment.TYPE)
public data class MilkyRawIncomingReplyMessageSegment
@MilkyEventModelConstructor
internal constructor(
    /**
     * 消息段数据。
     */
    public val data: Data,
) : MilkyRawIncomingMessageSegment {
    /**
     * 回复消息段数据。
     */
    @Serializable
    public data class Data
    @MilkyEventModelConstructor
    internal constructor(
        /**
         * 被引用的消息序列号
         */
        @SerialName("message_seq")
        public val messageSeq: Long,
        /**
         * 被引用的消息发送者 QQ 号
         */
        @SerialName("sender_id")
        public val senderId: Long,
        /**
         * 被引用的消息发送者名称，仅在合并转发中能够获取
         */
        @SerialName("sender_name")
        public val senderName: String? = null,
        /**
         * 被引用的消息的 Unix 时间戳（秒）
         */
        public val time: Long,
        /**
         * 被引用的消息内容
         */
        public val segments: List<MilkyRawIncomingMessageSegment>,
    )

    public companion object {
        public const val TYPE: String = "reply"
    }
}
