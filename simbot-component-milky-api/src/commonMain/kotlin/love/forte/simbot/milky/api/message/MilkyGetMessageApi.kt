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
import love.forte.simbot.milky.model.api.message.MilkyGetMessageParam
import love.forte.simbot.milky.model.api.message.MilkyGetMessageResponse
import love.forte.simbot.milky.model.api.message.MilkyMessageScene
import kotlin.jvm.JvmStatic

/**
 * [get_message 获取消息](https://milky.ntqqrev.org/api/message#get_message)
 *
 * @author Forte Scarlet
 */
public class MilkyGetMessageApi private constructor(
    private val param: MilkyGetMessageParam
) :
    SerializationBasedTypedMilkyApi<MilkyGetMessageResponse>(),
    MilkyMessageApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetMessageResponse>
        get() = MilkyGetMessageResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetMessageResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyGetMessageParam.serializer(), param)

    override fun toString(): String {
        return "MilkyGetMessageApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "get_message"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetMessageResponse.serializer())

        /**
         * 使用 [MilkyGetMessageParam] 构建 [MilkyGetMessageApi]。
         */
        @JvmStatic
        public fun create(param: MilkyGetMessageParam): MilkyGetMessageApi = MilkyGetMessageApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyGetMessageApi]。
         * @param messageScene 消息场景
         * @param peerId 好友 QQ 号或群号
         * @param messageSeq 消息序列号
         */
        @JvmStatic
        public fun create(messageScene: MilkyMessageScene, peerId: Long, messageSeq: Long): MilkyGetMessageApi =
            create(MilkyGetMessageParam(messageScene, peerId, messageSeq))
    }
}
