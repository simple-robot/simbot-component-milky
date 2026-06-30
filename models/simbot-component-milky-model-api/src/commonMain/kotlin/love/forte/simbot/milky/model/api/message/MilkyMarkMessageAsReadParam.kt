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
 * [mark_message_as_read 标记消息为已读](https://milky.ntqqrev.org/api/message#mark_message_as_read)
 * API 的请求入参。
 */
@Serializable
public class MilkyMarkMessageAsReadParam(
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
     * 标为已读的消息序列号，该消息及更早消息将被标记为已读
     */
    @SerialName("message_seq")
    public val messageSeq: Long,
) : MilkyApiParamModel {
    override fun toString(): String {
        return "MilkyMarkMessageAsReadParam(messageScene=$messageScene, peerId=$peerId, messageSeq=$messageSeq)"
    }
}
