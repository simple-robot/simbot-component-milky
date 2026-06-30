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
import love.forte.simbot.milky.model.api.system.MilkyGetGroupListParam
import love.forte.simbot.milky.model.api.system.MilkyGetGroupListResponse
import kotlin.jvm.JvmStatic

/**
 * [get_group_list 获取群列表](https://milky.ntqqrev.org/api/system#get_group_list)
 *
 * @author Forte Scarlet
 */
public class MilkyGetGroupListApi private constructor(
    private val param: MilkyGetGroupListParam
) :
    SerializationBasedTypedMilkyApi<MilkyGetGroupListResponse>(),
    MilkySystemApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetGroupListResponse>
        get() = MilkyGetGroupListResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetGroupListResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyGetGroupListParam.serializer(), param)

    override fun toString(): String {
        return "MilkyGetGroupListApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "get_group_list"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetGroupListResponse.serializer())

        /**
         * 使用 [MilkyGetGroupListParam] 构建 [MilkyGetGroupListApi]。
         */
        @JvmStatic
        public fun create(param: MilkyGetGroupListParam): MilkyGetGroupListApi = MilkyGetGroupListApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyGetGroupListApi]。
         * @param noCache 是否强制不使用缓存
         */
        @JvmStatic
        public fun create(noCache: Boolean = false): MilkyGetGroupListApi = create(MilkyGetGroupListParam(noCache))
    }
}
