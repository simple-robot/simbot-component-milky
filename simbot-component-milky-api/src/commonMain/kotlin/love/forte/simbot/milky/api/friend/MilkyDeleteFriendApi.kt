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

package love.forte.simbot.milky.api.friend

import love.forte.simbot.milky.api.MilkyApi
import love.forte.simbot.milky.api.UnitResultMilkyApi
import love.forte.simbot.milky.model.api.friend.MilkyDeleteFriendParam
import kotlin.jvm.JvmStatic

/**
 * [delete_friend 删除好友](https://milky.ntqqrev.org/api/friend#delete_friend)
 *
 * @author Forte Scarlet
 */
public class MilkyDeleteFriendApi private constructor(
    private val param: MilkyDeleteFriendParam
) : UnitResultMilkyApi(), MilkyFriendApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyDeleteFriendParam.serializer(), param)

    override fun toString(): String {
        return "MilkyDeleteFriendApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "delete_friend"

        /**
         * 使用 [MilkyDeleteFriendParam] 构建 [MilkyDeleteFriendApi]。
         */
        @JvmStatic
        public fun create(param: MilkyDeleteFriendParam): MilkyDeleteFriendApi = MilkyDeleteFriendApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyDeleteFriendApi]。
         * @param userId 好友 QQ 号
         */
        @JvmStatic
        public fun create(userId: Long): MilkyDeleteFriendApi = create(MilkyDeleteFriendParam(userId))
    }
}
