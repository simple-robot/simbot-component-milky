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
import love.forte.simbot.milky.model.api.message.MilkyOutgoingSegment
import love.forte.simbot.milky.model.api.message.MilkySendPrivateMessageParam
import love.forte.simbot.milky.model.api.message.MilkySendPrivateMessageResponse
import kotlin.jvm.JvmStatic

/**
 * [send_private_message 发送私聊消息](https://milky.ntqqrev.org/api/message#send_private_message)
 *
 * @author Forte Scarlet
 */
public class MilkySendPrivateMessageApi private constructor(
    private val param: MilkySendPrivateMessageParam
) :
    SerializationBasedTypedMilkyApi<MilkySendPrivateMessageResponse>(),
    MilkyMessageApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkySendPrivateMessageResponse>
        get() = MilkySendPrivateMessageResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkySendPrivateMessageResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkySendPrivateMessageParam.serializer(), param)

    override fun toString(): String {
        return "MilkySendPrivateMessageApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "send_private_message"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkySendPrivateMessageResponse.serializer())

        /**
         * 使用 [MilkySendPrivateMessageParam] 构建 [MilkySendPrivateMessageApi]。
         */
        @JvmStatic
        public fun create(param: MilkySendPrivateMessageParam): MilkySendPrivateMessageApi =
            MilkySendPrivateMessageApi(param)

        /**
         * 使用 API 入参字段构建 [MilkySendPrivateMessageApi]。
         * @param userId 好友 QQ 号
         * @param message 消息内容
         */
        @JvmStatic
        public fun create(userId: Long, message: List<MilkyOutgoingSegment>): MilkySendPrivateMessageApi =
            create(MilkySendPrivateMessageParam(userId, message))
    }
}
