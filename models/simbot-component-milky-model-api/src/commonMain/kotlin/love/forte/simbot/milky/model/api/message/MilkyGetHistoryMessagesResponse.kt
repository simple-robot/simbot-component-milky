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

package love.forte.simbot.milky.model.api.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.api.MilkyApiModelConstructor
import love.forte.simbot.milky.model.api.MilkyApiResponseModel

/**
 * [get_history_messages 获取历史消息列表](https://milky.ntqqrev.org/api/message#get_history_messages)
 * API 的响应体结构模型（输出参数）。
 */
@Serializable
public data class MilkyGetHistoryMessagesResponse
@MilkyApiModelConstructor private constructor(
    /**
     * 获取到的消息，按 message_seq 升序排列
     */
    val messages: List<MilkyIncomingMessage>,
    /**
     * 下一页起始消息序列号
     */
    @SerialName("next_message_seq")
    val nextMessageSeq: Long? = null,
) : MilkyApiResponseModel
