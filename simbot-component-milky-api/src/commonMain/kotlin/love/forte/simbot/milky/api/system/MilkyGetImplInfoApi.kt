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
import love.forte.simbot.milky.model.api.system.MilkyGetImplInfoResponse
import kotlin.jvm.JvmStatic

/**
 * [get_impl_info 获取协议端信息](https://milky.ntqqrev.org/api/system#get_impl_info)
 *
 * @author Forte Scarlet
 */
public class MilkyGetImplInfoApi private constructor() :
    SerializationBasedTypedMilkyApi<MilkyGetImplInfoResponse>(),
    MilkySystemApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetImplInfoResponse>
        get() = MilkyGetImplInfoResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetImplInfoResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String? = null
    override fun toString(): String {
        return "MilkyGetImplInfoApi(param={})"
    }

    public companion object {
        public const val API_NAME: String = "get_impl_info"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetImplInfoResponse.serializer())
        private val INSTANCE = MilkyGetImplInfoApi()

        /**
         * 获取 [MilkyGetImplInfoApi] 实例。
         */
        @JvmStatic
        public fun instance(): MilkyGetImplInfoApi = INSTANCE
    }
}
