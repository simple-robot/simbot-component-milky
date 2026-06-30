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
import love.forte.simbot.milky.model.api.group.MilkySetGroupMemberSpecialTitleParam
import kotlin.jvm.JvmStatic

/**
 * [set_group_member_special_title 设置群成员专属头衔](https://milky.ntqqrev.org/api/group#set_group_member_special_title)
 *
 * @author Forte Scarlet
 */
public class MilkySetGroupMemberSpecialTitleApi private constructor(
    private val param: MilkySetGroupMemberSpecialTitleParam
) : UnitResultMilkyApi(), MilkyGroupApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkySetGroupMemberSpecialTitleParam.serializer(), param)

    override fun toString(): String {
        return "MilkySetGroupMemberSpecialTitleApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "set_group_member_special_title"

        /**
         * 使用 [MilkySetGroupMemberSpecialTitleParam] 构建 [MilkySetGroupMemberSpecialTitleApi]。
         */
        @JvmStatic
        public fun create(param: MilkySetGroupMemberSpecialTitleParam): MilkySetGroupMemberSpecialTitleApi =
            MilkySetGroupMemberSpecialTitleApi(param)

        /**
         * 使用 API 入参字段构建 [MilkySetGroupMemberSpecialTitleApi]。
         * @param groupId 群号
         * @param userId 被设置的群成员 QQ 号
         * @param specialTitle 新专属头衔
         */
        @JvmStatic
        public fun create(groupId: Long, userId: Long, specialTitle: String): MilkySetGroupMemberSpecialTitleApi =
            create(MilkySetGroupMemberSpecialTitleParam(groupId, userId, specialTitle))
    }
}
