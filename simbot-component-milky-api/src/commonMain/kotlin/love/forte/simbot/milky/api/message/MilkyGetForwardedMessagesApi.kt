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
import love.forte.simbot.milky.model.api.message.MilkyGetForwardedMessagesParam
import love.forte.simbot.milky.model.api.message.MilkyGetForwardedMessagesResponse
import kotlin.jvm.JvmStatic

/**
 * [get_forwarded_messages 获取合并转发消息内容](https://milky.ntqqrev.org/api/message#get_forwarded_messages)
 *
 * @author Forte Scarlet
 */
public class MilkyGetForwardedMessagesApi private constructor(
    private val param: MilkyGetForwardedMessagesParam
) :
    SerializationBasedTypedMilkyApi<MilkyGetForwardedMessagesResponse>(),
    MilkyMessageApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetForwardedMessagesResponse>
        get() = MilkyGetForwardedMessagesResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetForwardedMessagesResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyGetForwardedMessagesParam.serializer(), param)

    override fun toString(): String {
        return "MilkyGetForwardedMessagesApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "get_forwarded_messages"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetForwardedMessagesResponse.serializer())

        /**
         * 使用 [MilkyGetForwardedMessagesParam] 构建 [MilkyGetForwardedMessagesApi]。
         */
        @JvmStatic
        public fun create(param: MilkyGetForwardedMessagesParam): MilkyGetForwardedMessagesApi =
            MilkyGetForwardedMessagesApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyGetForwardedMessagesApi]。
         * @param forwardId 转发消息 ID
         */
        @JvmStatic
        public fun create(forwardId: String): MilkyGetForwardedMessagesApi =
            create(MilkyGetForwardedMessagesParam(forwardId))
    }
}
