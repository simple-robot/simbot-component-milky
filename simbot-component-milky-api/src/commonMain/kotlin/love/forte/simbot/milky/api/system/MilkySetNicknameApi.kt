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

package love.forte.simbot.milky.api.system

import love.forte.simbot.milky.api.MilkyApi
import love.forte.simbot.milky.api.UnitResultMilkyApi
import love.forte.simbot.milky.model.api.system.MilkySetNicknameParam
import kotlin.jvm.JvmStatic

/**
 * [set_nickname 设置 QQ 账号昵称](https://milky.ntqqrev.org/api/system#set_nickname)
 *
 * @author Forte Scarlet
 */
public class MilkySetNicknameApi private constructor(
    private val param: MilkySetNicknameParam
) : UnitResultMilkyApi(), MilkySystemApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkySetNicknameParam.serializer(), param)

    override fun toString(): String {
        return "MilkySetNicknameApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "set_nickname"

        /**
         * 使用 [MilkySetNicknameParam] 构建 [MilkySetNicknameApi]。
         */
        @JvmStatic
        public fun create(param: MilkySetNicknameParam): MilkySetNicknameApi = MilkySetNicknameApi(param)

        /**
         * 使用 API 入参字段构建 [MilkySetNicknameApi]。
         * @param newNickname 新昵称
         */
        @JvmStatic
        public fun create(newNickname: String): MilkySetNicknameApi = create(MilkySetNicknameParam(newNickname))
    }
}
