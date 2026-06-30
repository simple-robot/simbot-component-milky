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
import love.forte.simbot.milky.model.api.group.MilkySetGroupMemberAdminParam
import kotlin.jvm.JvmStatic

/**
 * [set_group_member_admin 设置群管理员](https://milky.ntqqrev.org/api/group#set_group_member_admin)
 *
 * @author Forte Scarlet
 */
public class MilkySetGroupMemberAdminApi private constructor(
    private val param: MilkySetGroupMemberAdminParam
) : UnitResultMilkyApi(), MilkyGroupApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkySetGroupMemberAdminParam.serializer(), param)

    override fun toString(): String {
        return "MilkySetGroupMemberAdminApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "set_group_member_admin"

        /**
         * 使用 [MilkySetGroupMemberAdminParam] 构建 [MilkySetGroupMemberAdminApi]。
         */
        @JvmStatic
        public fun create(param: MilkySetGroupMemberAdminParam): MilkySetGroupMemberAdminApi =
            MilkySetGroupMemberAdminApi(param)

        /**
         * 使用 API 入参字段构建 [MilkySetGroupMemberAdminApi]。
         * @param groupId 群号
         * @param userId 被设置的 QQ 号
         * @param isSet 是否设置为管理员，false 表示取消管理员
         */
        @JvmStatic
        public fun create(groupId: Long, userId: Long, isSet: Boolean = true): MilkySetGroupMemberAdminApi =
            create(MilkySetGroupMemberAdminParam(groupId, userId, isSet))
    }
}
