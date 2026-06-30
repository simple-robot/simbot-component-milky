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

package love.forte.simbot.milky.model.api.system

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.api.MilkyApiParamModel
import love.forte.simbot.milky.model.api.message.MilkyMessageScene

/**
 * [set_peer_pin 设置好友或群的置顶状态](https://milky.ntqqrev.org/api/system#set_peer_pin)
 * API 的请求入参。
 */
@Serializable
public class MilkySetPeerPinParam(
    /**
     * 要设置的会话的消息场景
     */
    @SerialName("message_scene")
    public val messageScene: MilkyMessageScene,
    /**
     * 要设置的好友 QQ 号或群号
     */
    @SerialName("peer_id")
    public val peerId: Long,
    /**
     * 是否置顶，false 表示取消置顶
     */
    @SerialName("is_pinned")
    public val isPinned: Boolean = true,
) : MilkyApiParamModel {
    override fun toString(): String {
        return "MilkySetPeerPinParam(messageScene=$messageScene, peerId=$peerId, isPinned=$isPinned)"
    }
}
