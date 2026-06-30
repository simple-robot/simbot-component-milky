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

package love.forte.simbot.milky.model.api.system

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.api.MilkyApiParamModel

/**
 * [get_friend_info 获取好友信息](https://milky.ntqqrev.org/api/system#get_friend_info)
 * API 的请求入参。
 */
@Serializable
public class MilkyGetFriendInfoParam(
    /**
     * 好友 QQ 号
     */
    @SerialName("user_id")
    public val userId: Long,
    /**
     * 是否强制不使用缓存
     */
    @SerialName("no_cache")
    public val noCache: Boolean = false,
) : MilkyApiParamModel {
    override fun toString(): String {
        return "MilkyGetFriendInfoParam(userId=$userId, noCache=$noCache)"
    }
}
