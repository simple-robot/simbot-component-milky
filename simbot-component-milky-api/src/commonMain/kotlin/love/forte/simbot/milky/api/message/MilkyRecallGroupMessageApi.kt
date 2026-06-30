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
import love.forte.simbot.milky.model.api.message.MilkyRecallGroupMessageParam
import kotlin.jvm.JvmStatic

/**
 * [recall_group_message 撤回群聊消息](https://milky.ntqqrev.org/api/message#recall_group_message)
 *
 * @author Forte Scarlet
 */
public class MilkyRecallGroupMessageApi private constructor(
    private val param: MilkyRecallGroupMessageParam
) : UnitResultMilkyApi(), MilkyMessageApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyRecallGroupMessageParam.serializer(), param)

    override fun toString(): String {
        return "MilkyRecallGroupMessageApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "recall_group_message"

        /**
         * 使用 [MilkyRecallGroupMessageParam] 构建 [MilkyRecallGroupMessageApi]。
         */
        @JvmStatic
        public fun create(param: MilkyRecallGroupMessageParam): MilkyRecallGroupMessageApi =
            MilkyRecallGroupMessageApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyRecallGroupMessageApi]。
         * @param groupId 群号
         * @param messageSeq 消息序列号
         */
        @JvmStatic
        public fun create(groupId: Long, messageSeq: Long): MilkyRecallGroupMessageApi =
            create(MilkyRecallGroupMessageParam(groupId, messageSeq))
    }
}
