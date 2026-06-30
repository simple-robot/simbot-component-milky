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

package love.forte.simbot.milky.model.api.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.api.MilkyApiModelConstructor
import kotlin.jvm.JvmStatic

/**
 * [OutgoingForwardedMessage 发送转发消息](https://milky.ntqqrev.org/struct/OutgoingForwardedMessage)。
 */
@Serializable
public data class MilkyOutgoingForwardedMessage
@MilkyApiModelConstructor constructor(
    /**
     * 发送者 QQ 号。
     */
    @SerialName("user_id")
    val userId: Long,
    /**
     * 发送者名称。
     */
    @SerialName("sender_name")
    val senderName: String,
    /**
     * 消息段列表。
     */
    val segments: List<MilkyOutgoingSegment>,
) {
    public companion object {
        /**
         * 构建发送转发消息。
         */
        @JvmStatic
        public fun create(
            userId: Long,
            senderName: String,
            segments: List<MilkyOutgoingSegment>,
        ): MilkyOutgoingForwardedMessage = MilkyOutgoingForwardedMessage(userId, senderName, segments)
    }
}
