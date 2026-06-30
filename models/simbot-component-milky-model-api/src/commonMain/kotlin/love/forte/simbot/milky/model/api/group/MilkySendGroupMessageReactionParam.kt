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
import love.forte.simbot.milky.model.api.MilkyApiParamModel

/**
 * [send_group_message_reaction 发送群消息表情回应](https://milky.ntqqrev.org/api/group#send_group_message_reaction)
 * API 的请求入参。
 */
@Serializable
public class MilkySendGroupMessageReactionParam(
    /**
     * 群号
     */
    @SerialName("group_id")
    public val groupId: Long,
    /**
     * 要回应的消息序列号
     */
    @SerialName("message_seq")
    public val messageSeq: Long,
    /**
     * 发送的回应的表情 ID
     */
    public val reaction: String,
    /**
     * 发送的回应类型
     */
    @SerialName("reaction_type")
    public val reactionType: MilkyGroupMessageReactionType = MilkyGroupMessageReactionType.FACE,
    /**
     * 是否添加表情，false 表示取消
     */
    @SerialName("is_add")
    public val isAdd: Boolean = true,
) : MilkyApiParamModel {
    override fun toString(): String {
        return "MilkySendGroupMessageReactionParam(groupId=$groupId, messageSeq=$messageSeq, reaction='$reaction', reactionType=$reactionType, isAdd=$isAdd)"
    }
}
