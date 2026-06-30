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
import love.forte.simbot.milky.model.api.group.MilkyKickGroupMemberParam
import kotlin.jvm.JvmStatic

/**
 * [kick_group_member 踢出群成员](https://milky.ntqqrev.org/api/group#kick_group_member)
 *
 * @author Forte Scarlet
 */
public class MilkyKickGroupMemberApi private constructor(
    private val param: MilkyKickGroupMemberParam
) : UnitResultMilkyApi(), MilkyGroupApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyKickGroupMemberParam.serializer(), param)

    override fun toString(): String {
        return "MilkyKickGroupMemberApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "kick_group_member"

        /**
         * 使用 [MilkyKickGroupMemberParam] 构建 [MilkyKickGroupMemberApi]。
         */
        @JvmStatic
        public fun create(param: MilkyKickGroupMemberParam): MilkyKickGroupMemberApi = MilkyKickGroupMemberApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyKickGroupMemberApi]。
         * @param groupId 群号
         * @param userId 被踢的 QQ 号
         * @param rejectAddRequest 是否拒绝加群申请，false 表示不拒绝
         */
        @JvmStatic
        public fun create(groupId: Long, userId: Long, rejectAddRequest: Boolean = false): MilkyKickGroupMemberApi =
            create(MilkyKickGroupMemberParam(groupId, userId, rejectAddRequest))
    }
}
