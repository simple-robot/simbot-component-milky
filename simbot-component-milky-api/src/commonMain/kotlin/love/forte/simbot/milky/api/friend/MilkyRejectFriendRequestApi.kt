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
import love.forte.simbot.milky.model.api.friend.MilkyRejectFriendRequestParam
import kotlin.jvm.JvmStatic

/**
 * [reject_friend_request 拒绝好友请求](https://milky.ntqqrev.org/api/friend#reject_friend_request)
 *
 * @author Forte Scarlet
 */
public class MilkyRejectFriendRequestApi private constructor(
    private val param: MilkyRejectFriendRequestParam
) : UnitResultMilkyApi(), MilkyFriendApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyRejectFriendRequestParam.serializer(), param)

    override fun toString(): String {
        return "MilkyRejectFriendRequestApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "reject_friend_request"

        /**
         * 使用 [MilkyRejectFriendRequestParam] 构建 [MilkyRejectFriendRequestApi]。
         */
        @JvmStatic
        public fun create(param: MilkyRejectFriendRequestParam): MilkyRejectFriendRequestApi =
            MilkyRejectFriendRequestApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyRejectFriendRequestApi]。
         * @param initiatorUid 请求发起者 UID
         * @param isFiltered 是否是被过滤的请求
         * @param reason 拒绝理由
         */
        @JvmStatic
        public fun create(
            initiatorUid: String,
            isFiltered: Boolean = false,
            reason: String? = null
        ): MilkyRejectFriendRequestApi = create(MilkyRejectFriendRequestParam(initiatorUid, isFiltered, reason))

        /**
         * 使用 API 入参字段构建 [MilkyRejectFriendRequestApi]。
         * @param initiatorUid 请求发起者 UID
         */
        @JvmStatic
        public fun create(initiatorUid: String): MilkyRejectFriendRequestApi =
            create(MilkyRejectFriendRequestParam(initiatorUid))
    }
}
