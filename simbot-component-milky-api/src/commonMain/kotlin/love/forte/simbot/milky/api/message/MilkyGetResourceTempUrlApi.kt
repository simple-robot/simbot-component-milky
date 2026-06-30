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

package love.forte.simbot.milky.api.message

import kotlinx.serialization.KSerializer
import love.forte.simbot.milky.api.MilkyApi
import love.forte.simbot.milky.api.SerializationBasedTypedMilkyApi
import love.forte.simbot.milky.model.api.MilkyApiResult
import love.forte.simbot.milky.model.api.message.MilkyGetResourceTempUrlParam
import love.forte.simbot.milky.model.api.message.MilkyGetResourceTempUrlResponse
import kotlin.jvm.JvmStatic

/**
 * [get_resource_temp_url 获取临时资源链接](https://milky.ntqqrev.org/api/message#get_resource_temp_url)
 *
 * @author Forte Scarlet
 */
public class MilkyGetResourceTempUrlApi private constructor(
    private val param: MilkyGetResourceTempUrlParam
) :
    SerializationBasedTypedMilkyApi<MilkyGetResourceTempUrlResponse>(),
    MilkyMessageApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetResourceTempUrlResponse>
        get() = MilkyGetResourceTempUrlResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetResourceTempUrlResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyGetResourceTempUrlParam.serializer(), param)

    override fun toString(): String {
        return "MilkyGetResourceTempUrlApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "get_resource_temp_url"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetResourceTempUrlResponse.serializer())

        /**
         * 使用 [MilkyGetResourceTempUrlParam] 构建 [MilkyGetResourceTempUrlApi]。
         */
        @JvmStatic
        public fun create(param: MilkyGetResourceTempUrlParam): MilkyGetResourceTempUrlApi =
            MilkyGetResourceTempUrlApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyGetResourceTempUrlApi]。
         * @param resourceId 资源 ID
         */
        @JvmStatic
        public fun create(resourceId: String): MilkyGetResourceTempUrlApi =
            create(MilkyGetResourceTempUrlParam(resourceId))
    }
}
