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
import love.forte.simbot.milky.model.api.message.MilkyGetHistoryMessagesParam
import love.forte.simbot.milky.model.api.message.MilkyGetHistoryMessagesResponse
import love.forte.simbot.milky.model.api.message.MilkyMessageScene
import kotlin.jvm.JvmStatic

/**
 * [get_history_messages 获取历史消息列表](https://milky.ntqqrev.org/api/message#get_history_messages)
 *
 * @author Forte Scarlet
 */
public class MilkyGetHistoryMessagesApi private constructor(
    private val param: MilkyGetHistoryMessagesParam
) :
    SerializationBasedTypedMilkyApi<MilkyGetHistoryMessagesResponse>(),
    MilkyMessageApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetHistoryMessagesResponse>
        get() = MilkyGetHistoryMessagesResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetHistoryMessagesResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyGetHistoryMessagesParam.serializer(), param)

    override fun toString(): String {
        return "MilkyGetHistoryMessagesApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "get_history_messages"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetHistoryMessagesResponse.serializer())

        /**
         * 使用 [MilkyGetHistoryMessagesParam] 构建 [MilkyGetHistoryMessagesApi]。
         */
        @JvmStatic
        public fun create(param: MilkyGetHistoryMessagesParam): MilkyGetHistoryMessagesApi =
            MilkyGetHistoryMessagesApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyGetHistoryMessagesApi]。
         * @param messageScene 消息场景
         * @param peerId 好友 QQ 号或群号
         * @param startMessageSeq 起始消息序列号，由此开始从新到旧查询；不提供则从最新消息开始
         * @param limit 期望获取到的消息数量，最多 30 条
         */
        @JvmStatic
        public fun create(
            messageScene: MilkyMessageScene,
            peerId: Long,
            startMessageSeq: Long? = null,
            limit: Int = 20
        ): MilkyGetHistoryMessagesApi =
            create(MilkyGetHistoryMessagesParam(messageScene, peerId, startMessageSeq, limit))
    }
}
