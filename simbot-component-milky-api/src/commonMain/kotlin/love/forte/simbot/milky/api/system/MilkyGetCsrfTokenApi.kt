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
import love.forte.simbot.milky.api.SerializationBasedTypedMilkyApi
import love.forte.simbot.milky.model.api.MilkyApiResult
import love.forte.simbot.milky.model.api.system.MilkyGetCsrfTokenResponse
import kotlin.jvm.JvmStatic

/**
 * [get_csrf_token 获取 CSRF Token](https://milky.ntqqrev.org/api/system#get_csrf_token)
 *
 * @author Forte Scarlet
 */
public class MilkyGetCsrfTokenApi private constructor() :
    SerializationBasedTypedMilkyApi<MilkyGetCsrfTokenResponse>(),
    MilkySystemApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetCsrfTokenResponse>
        get() = MilkyGetCsrfTokenResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetCsrfTokenResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String? = null
    override fun toString(): String {
        return "MilkyGetCsrfTokenApi(param={})"
    }

    public companion object {
        public const val API_NAME: String = "get_csrf_token"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetCsrfTokenResponse.serializer())
        private val INSTANCE = MilkyGetCsrfTokenApi()

        /**
         * 获取 [MilkyGetCsrfTokenApi] 实例。
         */
        @JvmStatic
        public fun instance(): MilkyGetCsrfTokenApi = INSTANCE
    }
}
