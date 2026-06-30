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
import love.forte.simbot.milky.model.api.group.MilkySetGroupNameParam
import kotlin.jvm.JvmStatic

/**
 * [set_group_name 设置群名称](https://milky.ntqqrev.org/api/group#set_group_name)
 *
 * @author Forte Scarlet
 */
public class MilkySetGroupNameApi private constructor(
    private val param: MilkySetGroupNameParam
) : UnitResultMilkyApi(), MilkyGroupApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkySetGroupNameParam.serializer(), param)

    override fun toString(): String {
        return "MilkySetGroupNameApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "set_group_name"

        /**
         * 使用 [MilkySetGroupNameParam] 构建 [MilkySetGroupNameApi]。
         */
        @JvmStatic
        public fun create(param: MilkySetGroupNameParam): MilkySetGroupNameApi = MilkySetGroupNameApi(param)

        /**
         * 使用 API 入参字段构建 [MilkySetGroupNameApi]。
         * @param groupId 群号
         * @param newGroupName 新群名称
         */
        @JvmStatic
        public fun create(groupId: Long, newGroupName: String): MilkySetGroupNameApi =
            create(MilkySetGroupNameParam(groupId, newGroupName))
    }
}
