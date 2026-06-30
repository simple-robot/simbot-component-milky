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

import love.forte.simbot.milky.api.MilkyApi
import love.forte.simbot.milky.api.UnitResultMilkyApi
import love.forte.simbot.milky.model.api.message.MilkyMarkMessageAsReadParam
import love.forte.simbot.milky.model.api.message.MilkyMessageScene
import kotlin.jvm.JvmStatic

/**
 * [mark_message_as_read 标记消息为已读](https://milky.ntqqrev.org/api/message#mark_message_as_read)
 *
 * @author Forte Scarlet
 */
public class MilkyMarkMessageAsReadApi private constructor(
    private val param: MilkyMarkMessageAsReadParam
) : UnitResultMilkyApi(), MilkyMessageApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyMarkMessageAsReadParam.serializer(), param)

    override fun toString(): String {
        return "MilkyMarkMessageAsReadApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "mark_message_as_read"

        /**
         * 使用 [MilkyMarkMessageAsReadParam] 构建 [MilkyMarkMessageAsReadApi]。
         */
        @JvmStatic
        public fun create(param: MilkyMarkMessageAsReadParam): MilkyMarkMessageAsReadApi =
            MilkyMarkMessageAsReadApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyMarkMessageAsReadApi]。
         * @param messageScene 消息场景
         * @param peerId 好友 QQ 号或群号
         * @param messageSeq 标为已读的消息序列号，该消息及更早消息将被标记为已读
         */
        @JvmStatic
        public fun create(messageScene: MilkyMessageScene, peerId: Long, messageSeq: Long): MilkyMarkMessageAsReadApi =
            create(MilkyMarkMessageAsReadParam(messageScene, peerId, messageSeq))
    }
}
