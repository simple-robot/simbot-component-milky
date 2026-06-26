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
 * [friend_nudge 好友戳一戳事件](https://milky.ntqqrev.org/struct/Event#type-friend_nudge).
 *
 * @see love.forte.simbot.milky.model.event.MilkyRawEvent
 */
@Serializable
@SerialName(MilkyRawFriendNudgeEventData.SERIAL_NAME)
@MilkyRawEventDataMarker(eventType = MilkyRawFriendNudgeEventData.EVENT_TYPE)
public data class MilkyRawFriendNudgeEventData
@MilkyEventModelConstructor
internal constructor(
    /**
     * 好友 QQ 号
     */
    @SerialName("user_id")
    public val userId: Long,
    /**
     * 是否是自己发送的戳一戳
     */
    @SerialName("is_self_send")
    public val isSelfSend: Boolean,
    /**
     * 是否是自己接收的戳一戳
     */
    @SerialName("is_self_receive")
    public val isSelfReceive: Boolean,
    /**
     * 戳一戳提示的动作文本
     */
    @SerialName("display_action")
    public val displayAction: String,
    /**
     * 戳一戳提示的后缀文本
     */
    @SerialName("display_suffix")
    public val displaySuffix: String,
    /**
     * 戳一戳提示的动作图片 URL
     */
    @SerialName("display_action_img_url")
    public val displayActionImgUrl: String,
) : MilkyRawEventData() {
    public companion object {
        public const val EVENT_TYPE: String = "friend_nudge"
        public const val SERIAL_NAME: String = SERIAL_NAME_PREFIX + EVENT_TYPE
    }
}
