/*
 *     Copyright (c) 2026. ForteScarlet.
 *
 *     Project    https://github.com/simple-robot/simpler-robot
 *     Email      ForteScarlet@163.com
 *
 *     This file is part of the Simple Robot Library (Alias: simple-robot, simbot, etc.).
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
 *     You should have received a copy of the Lesser GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package love.forte.simbot.milky.api.system

import love.forte.simbot.milky.api.MilkyApi
import love.forte.simbot.milky.api.UnitResultMilkyApi
import love.forte.simbot.milky.model.api.system.MilkySetAvatarParam
import kotlin.jvm.JvmStatic

/**
 * [set_avatar 设置 QQ 账号头像](https://milky.ntqqrev.org/api/system#set_avatar)
 *
 * @author Forte Scarlet
 */
public class MilkySetAvatarApi private constructor(
    private val param: MilkySetAvatarParam
) : UnitResultMilkyApi(), MilkySystemApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkySetAvatarParam.serializer(), param)

    override fun toString(): String {
        return "MilkySetAvatarApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "set_avatar"

        /**
         * 使用 [MilkySetAvatarParam] 构建 [MilkySetAvatarApi]。
         *
         * 注: 代码中不会校验 uri 的合法性，交给 API 服务端校验。
         */
        @JvmStatic
        public fun create(param: MilkySetAvatarParam): MilkySetAvatarApi = MilkySetAvatarApi(param)

        /**
         * 使用 uri 构建 [MilkySetAvatarApi]。
         *
         * 注: 代码中不会校验 uri 的合法性，交给 API 服务端校验。
         *
         * @param uri 参考 [MilkySetAvatarParam.uri] 的说明。
         */
        @JvmStatic
        public fun create(uri: String): MilkySetAvatarApi = create(MilkySetAvatarParam(uri))
    }
}
