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
 * [set_group_essence_message 设置群精华消息](https://milky.ntqqrev.org/api/group#set_group_essence_message)
 * API 的请求入参。
 */
@Serializable
public class MilkySetGroupEssenceMessageParam(
    /**
     * 群号
     */
    @SerialName("group_id")
    public val groupId: Long,
    /**
     * 消息序列号
     */
    @SerialName("message_seq")
    public val messageSeq: Long,
    /**
     * 是否设置为精华消息，false 表示取消精华
     */
    @SerialName("is_set")
    public val isSet: Boolean = true,
) : MilkyApiParamModel {
    override fun toString(): String {
        return "MilkySetGroupEssenceMessageParam(groupId=$groupId, messageSeq=$messageSeq, isSet=$isSet)"
    }
}
