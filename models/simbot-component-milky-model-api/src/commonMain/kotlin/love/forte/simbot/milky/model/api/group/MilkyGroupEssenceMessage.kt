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

package love.forte.simbot.milky.model.api.group

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.api.MilkyApiModelConstructor
import love.forte.simbot.milky.model.api.MilkyApiResponseModel
import love.forte.simbot.milky.model.api.message.MilkyIncomingSegment

/**
 * [GroupEssenceMessage 群精华消息](https://milky.ntqqrev.org/struct/GroupEssenceMessage)
 */
@Serializable
public data class MilkyGroupEssenceMessage
@MilkyApiModelConstructor private constructor(
    /**
     * 群号
     */
    @SerialName("group_id")
    val groupId: Long,
    /**
     * 消息序列号
     */
    @SerialName("message_seq")
    val messageSeq: Long,
    /**
     * 消息发送时的 Unix 时间戳（秒）
     */
    @SerialName("message_time")
    val messageTime: Long,
    /**
     * 发送者 QQ 号
     */
    @SerialName("sender_id")
    val senderId: Long,
    /**
     * 发送者名称
     */
    @SerialName("sender_name")
    val senderName: String,
    /**
     * 设置精华的操作者 QQ 号
     */
    @SerialName("operator_id")
    val operatorId: Long,
    /**
     * 设置精华的操作者名称
     */
    @SerialName("operator_name")
    val operatorName: String,
    /**
     * 消息被设置精华时的 Unix 时间戳（秒）
     */
    @SerialName("operation_time")
    val operationTime: Long,
    /**
     * 消息段列表
     */
    val segments: List<MilkyIncomingSegment>,
) : MilkyApiResponseModel
