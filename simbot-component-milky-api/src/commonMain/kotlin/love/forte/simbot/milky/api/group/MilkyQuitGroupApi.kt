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
import love.forte.simbot.milky.model.api.group.MilkyQuitGroupParam
import kotlin.jvm.JvmStatic

/**
 * [quit_group 退出群](https://milky.ntqqrev.org/api/group#quit_group)
 *
 * @author Forte Scarlet
 */
public class MilkyQuitGroupApi private constructor(
    private val param: MilkyQuitGroupParam
) : UnitResultMilkyApi(), MilkyGroupApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyQuitGroupParam.serializer(), param)

    override fun toString(): String {
        return "MilkyQuitGroupApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "quit_group"

        /**
         * 使用 [MilkyQuitGroupParam] 构建 [MilkyQuitGroupApi]。
         */
        @JvmStatic
        public fun create(param: MilkyQuitGroupParam): MilkyQuitGroupApi = MilkyQuitGroupApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyQuitGroupApi]。
         * @param groupId 群号
         */
        @JvmStatic
        public fun create(groupId: Long): MilkyQuitGroupApi = create(MilkyQuitGroupParam(groupId))
    }
}
