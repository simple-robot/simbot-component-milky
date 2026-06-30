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
 * [kick_group_member 踢出群成员](https://milky.ntqqrev.org/api/group#kick_group_member)
 * API 的请求入参。
 */
@Serializable
public class MilkyKickGroupMemberParam(
    /**
     * 群号
     */
    @SerialName("group_id")
    public val groupId: Long,
    /**
     * 被踢的 QQ 号
     */
    @SerialName("user_id")
    public val userId: Long,
    /**
     * 是否拒绝加群申请，false 表示不拒绝
     */
    @SerialName("reject_add_request")
    public val rejectAddRequest: Boolean = false,
) : MilkyApiParamModel {
    override fun toString(): String {
        return "MilkyKickGroupMemberParam(groupId=$groupId, userId=$userId, rejectAddRequest=$rejectAddRequest)"
    }
}
