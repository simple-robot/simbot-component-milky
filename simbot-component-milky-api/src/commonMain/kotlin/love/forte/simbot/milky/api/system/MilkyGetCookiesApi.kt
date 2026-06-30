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

import kotlinx.serialization.KSerializer
import love.forte.simbot.milky.api.MilkyApi
import love.forte.simbot.milky.api.SerializationBasedTypedMilkyApi
import love.forte.simbot.milky.model.api.MilkyApiResult
import love.forte.simbot.milky.model.api.system.MilkyGetCookiesParam
import love.forte.simbot.milky.model.api.system.MilkyGetCookiesResponse
import kotlin.jvm.JvmStatic

/**
 * [get_cookies 获取 Cookies](https://milky.ntqqrev.org/api/system#get_cookies)
 *
 * @author Forte Scarlet
 */
public class MilkyGetCookiesApi private constructor(
    private val param: MilkyGetCookiesParam
) :
    SerializationBasedTypedMilkyApi<MilkyGetCookiesResponse>(),
    MilkySystemApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetCookiesResponse>
        get() = MilkyGetCookiesResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetCookiesResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyGetCookiesParam.serializer(), param)

    override fun toString(): String {
        return "MilkyGetCookiesApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "get_cookies"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetCookiesResponse.serializer())

        /**
         * 使用 [MilkyGetCookiesParam] 构建 [MilkyGetCookiesApi]。
         */
        @JvmStatic
        public fun create(param: MilkyGetCookiesParam): MilkyGetCookiesApi = MilkyGetCookiesApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyGetCookiesApi]。
         * @param domain 需要获取 Cookies 的域名
         */
        @JvmStatic
        public fun create(domain: String): MilkyGetCookiesApi = create(MilkyGetCookiesParam(domain))
    }
}
