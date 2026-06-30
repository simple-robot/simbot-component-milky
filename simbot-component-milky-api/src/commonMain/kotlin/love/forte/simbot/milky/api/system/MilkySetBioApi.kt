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
import love.forte.simbot.milky.model.api.system.MilkySetBioParam
import kotlin.jvm.JvmStatic

/**
 * [set_bio 设置 QQ 账号个性签名](https://milky.ntqqrev.org/api/system#set_bio)
 *
 * @author Forte Scarlet
 */
public class MilkySetBioApi private constructor(
    private val param: MilkySetBioParam
) : UnitResultMilkyApi(), MilkySystemApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkySetBioParam.serializer(), param)

    override fun toString(): String {
        return "MilkySetBioApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "set_bio"

        /**
         * 使用 [MilkySetBioParam] 构建 [MilkySetBioApi]。
         */
        @JvmStatic
        public fun create(param: MilkySetBioParam): MilkySetBioApi = MilkySetBioApi(param)

        /**
         * 使用 API 入参字段构建 [MilkySetBioApi]。
         * @param newBio 新个性签名
         */
        @JvmStatic
        public fun create(newBio: String): MilkySetBioApi = create(MilkySetBioParam(newBio))
    }
}
