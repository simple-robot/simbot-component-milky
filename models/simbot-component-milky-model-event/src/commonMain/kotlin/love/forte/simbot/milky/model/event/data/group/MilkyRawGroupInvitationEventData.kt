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

package love.forte.simbot.milky.model.event.data.group

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.event.MilkyEventModelConstructor
import love.forte.simbot.milky.model.event.data.MilkyRawEventData
import love.forte.simbot.milky.model.event.data.MilkyRawEventDataMarker

/**
 * [group_invitation 他人邀请自身入群事件](https://milky.ntqqrev.org/struct/Event#type-group_invitation).
 *
 * @see love.forte.simbot.milky.model.event.MilkyRawEvent
 */
@Serializable
@SerialName(MilkyRawGroupInvitationEventData.SERIAL_NAME)
@MilkyRawEventDataMarker(eventType = MilkyRawGroupInvitationEventData.EVENT_TYPE)
public data class MilkyRawGroupInvitationEventData
@MilkyEventModelConstructor
internal constructor(
    /**
     * 群号
     */
    @SerialName("group_id")
    public val groupId: Long,
    /**
     * 邀请序列号
     */
    @SerialName("invitation_seq")
    public val invitationSeq: Long,
    /**
     * 邀请者 QQ 号
     */
    @SerialName("initiator_id")
    public val initiatorId: Long,
    /**
     * 来源群号，如果是通过 QQ 群邀请
     */
    @SerialName("source_group_id")
    public val sourceGroupId: Long? = null,
) : MilkyRawEventData() {
    public companion object {
        public const val EVENT_TYPE: String = "group_invitation"
        public const val SERIAL_NAME: String = SERIAL_NAME_PREFIX + EVENT_TYPE
    }
}
