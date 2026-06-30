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

import kotlinx.serialization.KSerializer
import love.forte.simbot.milky.api.MilkyApi
import love.forte.simbot.milky.api.SerializationBasedTypedMilkyApi
import love.forte.simbot.milky.model.api.MilkyApiResult
import love.forte.simbot.milky.model.api.friend.MilkyGetFriendRequestsParam
import love.forte.simbot.milky.model.api.friend.MilkyGetFriendRequestsResponse
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

/**
 * [get_friend_requests 获取好友请求列表](https://milky.ntqqrev.org/api/friend#get_friend_requests)
 *
 * @author Forte Scarlet
 */
public class MilkyGetFriendRequestsApi private constructor(
    private val param: MilkyGetFriendRequestsParam
) :
    SerializationBasedTypedMilkyApi<MilkyGetFriendRequestsResponse>(),
    MilkyFriendApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetFriendRequestsResponse>
        get() = MilkyGetFriendRequestsResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetFriendRequestsResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyGetFriendRequestsParam.serializer(), param)

    override fun toString(): String {
        return "MilkyGetFriendRequestsApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "get_friend_requests"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetFriendRequestsResponse.serializer())

        /**
         * 使用 [MilkyGetFriendRequestsParam] 构建 [MilkyGetFriendRequestsApi]。
         */
        @JvmStatic
        public fun create(param: MilkyGetFriendRequestsParam): MilkyGetFriendRequestsApi =
            MilkyGetFriendRequestsApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyGetFriendRequestsApi]。
         * @param limit 获取的最大请求数量
         * @param isFiltered true 表示只获取被过滤的通知，false 表示只获取未被过滤的通知
         */
        @JvmStatic
        @JvmOverloads
        public fun create(limit: Int = 20, isFiltered: Boolean = false): MilkyGetFriendRequestsApi =
            create(MilkyGetFriendRequestsParam(limit, isFiltered))
    }
}
