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
import love.forte.simbot.milky.model.api.message.MilkySendGroupMessageParam
import love.forte.simbot.milky.model.api.message.MilkySendGroupMessageResponse
import kotlin.jvm.JvmStatic

/**
 * [send_group_message 发送群聊消息](https://milky.ntqqrev.org/api/message#send_group_message)
 *
 * @author Forte Scarlet
 */
public class MilkySendGroupMessageApi private constructor(
    private val param: MilkySendGroupMessageParam
) :
    SerializationBasedTypedMilkyApi<MilkySendGroupMessageResponse>(),
    MilkyMessageApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkySendGroupMessageResponse>
        get() = MilkySendGroupMessageResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkySendGroupMessageResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkySendGroupMessageParam.serializer(), param)

    override fun toString(): String {
        return "MilkySendGroupMessageApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "send_group_message"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkySendGroupMessageResponse.serializer())

        /**
         * 使用 [MilkySendGroupMessageParam] 构建 [MilkySendGroupMessageApi]。
         */
        @JvmStatic
        public fun create(param: MilkySendGroupMessageParam): MilkySendGroupMessageApi = MilkySendGroupMessageApi(param)

        /**
         * 使用 API 入参字段构建 [MilkySendGroupMessageApi]。
         * @param groupId 群号
         * @param message 消息内容
         */
        @JvmStatic
        public fun create(groupId: Long, message: List<MilkyOutgoingSegment>): MilkySendGroupMessageApi =
            create(MilkySendGroupMessageParam(groupId, message))
    }
}
