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

package love.forte.simbot.milky.api.group

import love.forte.simbot.milky.api.MilkyApi
import love.forte.simbot.milky.api.UnitResultMilkyApi
import love.forte.simbot.milky.model.api.group.MilkySendGroupNudgeParam
import kotlin.jvm.JvmStatic

/**
 * [send_group_nudge 发送群戳一戳](https://milky.ntqqrev.org/api/group#send_group_nudge)
 *
 * @author Forte Scarlet
 */
public class MilkySendGroupNudgeApi private constructor(
    private val param: MilkySendGroupNudgeParam
) : UnitResultMilkyApi(), MilkyGroupApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkySendGroupNudgeParam.serializer(), param)

    override fun toString(): String {
        return "MilkySendGroupNudgeApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "send_group_nudge"

        /**
         * 使用 [MilkySendGroupNudgeParam] 构建 [MilkySendGroupNudgeApi]。
         */
        @JvmStatic
        public fun create(param: MilkySendGroupNudgeParam): MilkySendGroupNudgeApi = MilkySendGroupNudgeApi(param)

        /**
         * 使用 API 入参字段构建 [MilkySendGroupNudgeApi]。
         * @param groupId 群号
         * @param userId 被戳的群成员 QQ 号
         */
        @JvmStatic
        public fun create(groupId: Long, userId: Long): MilkySendGroupNudgeApi =
            create(MilkySendGroupNudgeParam(groupId, userId))
    }
}
