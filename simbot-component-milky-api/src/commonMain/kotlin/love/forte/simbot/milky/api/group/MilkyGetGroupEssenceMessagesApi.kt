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

import kotlinx.serialization.KSerializer
import love.forte.simbot.milky.api.MilkyApi
import love.forte.simbot.milky.api.SerializationBasedTypedMilkyApi
import love.forte.simbot.milky.model.api.MilkyApiResult
import love.forte.simbot.milky.model.api.group.MilkyGetGroupEssenceMessagesParam
import love.forte.simbot.milky.model.api.group.MilkyGetGroupEssenceMessagesResponse
import kotlin.jvm.JvmStatic

/**
 * [get_group_essence_messages 获取群精华消息列表](https://milky.ntqqrev.org/api/group#get_group_essence_messages)
 *
 * @author Forte Scarlet
 */
public class MilkyGetGroupEssenceMessagesApi private constructor(
    private val param: MilkyGetGroupEssenceMessagesParam
) :
    SerializationBasedTypedMilkyApi<MilkyGetGroupEssenceMessagesResponse>(),
    MilkyGroupApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetGroupEssenceMessagesResponse>
        get() = MilkyGetGroupEssenceMessagesResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetGroupEssenceMessagesResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyGetGroupEssenceMessagesParam.serializer(), param)

    override fun toString(): String {
        return "MilkyGetGroupEssenceMessagesApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "get_group_essence_messages"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetGroupEssenceMessagesResponse.serializer())

        /**
         * 使用 [MilkyGetGroupEssenceMessagesParam] 构建 [MilkyGetGroupEssenceMessagesApi]。
         */
        @JvmStatic
        public fun create(param: MilkyGetGroupEssenceMessagesParam): MilkyGetGroupEssenceMessagesApi =
            MilkyGetGroupEssenceMessagesApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyGetGroupEssenceMessagesApi]。
         * @param groupId 群号
         * @param pageIndex 页码索引，从 0 开始
         * @param pageSize 每页包含的精华消息数量
         */
        @JvmStatic
        public fun create(groupId: Long, pageIndex: Int, pageSize: Int): MilkyGetGroupEssenceMessagesApi =
            create(MilkyGetGroupEssenceMessagesParam(groupId, pageIndex, pageSize))
    }
}
