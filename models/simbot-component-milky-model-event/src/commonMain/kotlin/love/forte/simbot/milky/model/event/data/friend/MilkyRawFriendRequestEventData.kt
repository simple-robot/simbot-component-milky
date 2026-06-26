/*
 *     Copyright (c) 2026. ForteScarlet.
 *
 *     Project    https://github.com/simple-robot/simpler-robot
 *     Email      ForteScarlet@163.com
 *
 *     This file is part of the Simple Robot Library (Alias: simple-robot, simbot, etc.).
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
 *     You should have received a copy of the Lesser GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package love.forte.simbot.milky.model.event.data.friend

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.event.MilkyEventModelConstructor
import love.forte.simbot.milky.model.event.data.MilkyRawEventData
import love.forte.simbot.milky.model.event.data.MilkyRawEventDataMarker

/**
 * [friend_request 好友请求事件](https://milky.ntqqrev.org/struct/Event#type-friend_request).
 *
 * @see love.forte.simbot.milky.model.event.MilkyRawEvent
 */
@Serializable
@SerialName(MilkyRawFriendRequestEventData.SERIAL_NAME)
@MilkyRawEventDataMarker(eventType = MilkyRawFriendRequestEventData.EVENT_TYPE)
public data class MilkyRawFriendRequestEventData
@MilkyEventModelConstructor
internal constructor(
    /**
     * 申请好友的用户 QQ 号
     */
    @SerialName("initiator_id")
    public val initiatorId: Long,
    /**
     * 用户 UID
     */
    @SerialName("initiator_uid")
    public val initiatorUid: String,
    /**
     * 申请附加信息
     */
    public val comment: String,
    /**
     * 申请来源
     */
    public val via: String,
) : MilkyRawEventData() {
    public companion object {
        public const val EVENT_TYPE: String = "friend_request"
        public const val SERIAL_NAME: String = SERIAL_NAME_PREFIX + EVENT_TYPE
    }
}
