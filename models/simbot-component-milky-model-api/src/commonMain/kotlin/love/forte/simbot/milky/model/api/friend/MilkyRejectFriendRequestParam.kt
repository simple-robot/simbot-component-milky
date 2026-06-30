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

package love.forte.simbot.milky.model.api.friend

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.api.MilkyApiParamModel

/**
 * [reject_friend_request 拒绝好友请求](https://milky.ntqqrev.org/api/friend#reject_friend_request)
 * API 的请求入参。
 */
@Serializable
public class MilkyRejectFriendRequestParam(
    /**
     * 请求发起者 UID
     */
    @SerialName("initiator_uid")
    public val initiatorUid: String,
    /**
     * 是否是被过滤的请求
     */
    @SerialName("is_filtered")
    public val isFiltered: Boolean = false,
    /**
     * 拒绝理由
     */
    public val reason: String? = null,
) : MilkyApiParamModel {
    override fun toString(): String {
        return "MilkyRejectFriendRequestParam(initiatorUid='$initiatorUid', isFiltered=$isFiltered, reason='$reason')"
    }
}
