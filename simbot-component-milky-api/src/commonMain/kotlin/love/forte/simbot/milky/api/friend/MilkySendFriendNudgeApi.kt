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
import love.forte.simbot.milky.model.api.friend.MilkySendFriendNudgeParam
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

/**
 * [send_friend_nudge 发送好友戳一戳](https://milky.ntqqrev.org/api/friend#send_friend_nudge)
 *
 * @author Forte Scarlet
 */
public class MilkySendFriendNudgeApi private constructor(
    private val param: MilkySendFriendNudgeParam
) : UnitResultMilkyApi(), MilkyFriendApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkySendFriendNudgeParam.serializer(), param)

    override fun toString(): String {
        return "MilkySendFriendNudgeApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "send_friend_nudge"

        /**
         * 使用 [MilkySendFriendNudgeParam] 构建 [MilkySendFriendNudgeApi]。
         */
        @JvmStatic
        public fun create(param: MilkySendFriendNudgeParam): MilkySendFriendNudgeApi = MilkySendFriendNudgeApi(param)

        /**
         * 使用 API 入参字段构建 [MilkySendFriendNudgeApi]。
         * @param userId 好友 QQ 号
         * @param isSelf 是否戳自己
         */
        @JvmStatic
        @JvmOverloads
        public fun create(userId: Long, isSelf: Boolean = false): MilkySendFriendNudgeApi =
            create(MilkySendFriendNudgeParam(userId, isSelf))
    }
}
