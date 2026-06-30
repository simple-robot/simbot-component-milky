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
import love.forte.simbot.milky.model.api.MilkyApiParamModel

/**
 * [get_history_messages 获取历史消息列表](https://milky.ntqqrev.org/api/message#get_history_messages)
 * API 的请求入参。
 */
@Serializable
public class MilkyGetHistoryMessagesParam(
    /**
     * 消息场景
     */
    @SerialName("message_scene")
    public val messageScene: MilkyMessageScene,
    /**
     * 好友 QQ 号或群号
     */
    @SerialName("peer_id")
    public val peerId: Long,
    /**
     * 起始消息序列号，由此开始从新到旧查询；不提供则从最新消息开始
     */
    @SerialName("start_message_seq")
    public val startMessageSeq: Long? = null,
    /**
     * 期望获取到的消息数量，最多 30 条
     */
    public val limit: Int = 20,
) : MilkyApiParamModel {
    override fun toString(): String {
        return "MilkyGetHistoryMessagesParam(messageScene=$messageScene, peerId=$peerId, startMessageSeq=$startMessageSeq, limit=$limit)"
    }
}
