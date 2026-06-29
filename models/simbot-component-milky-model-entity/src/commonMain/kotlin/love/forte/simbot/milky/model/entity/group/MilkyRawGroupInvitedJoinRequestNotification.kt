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

package love.forte.simbot.milky.model.entity.group

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.entity.MilkyEntityModelConstructor
import love.forte.simbot.milky.model.entity.MilkyRawRequestState

/**
 * [invited_join_request 群成员邀请他人入群请求](https://milky.ntqqrev.org/struct/GroupNotification#type-invited_join_request).
 */
@Serializable
@SerialName(MilkyRawGroupInvitedJoinRequestNotification.TYPE)
public data class MilkyRawGroupInvitedJoinRequestNotification
@MilkyEntityModelConstructor
internal constructor(
    /**
     * 群号
     */
    @SerialName("group_id")
    override val groupId: Long,
    /**
     * 通知序列号
     */
    @SerialName("notification_seq")
    override val notificationSeq: Long,
    /**
     * 邀请者 QQ 号
     */
    @SerialName("initiator_id")
    public val initiatorId: Long,
    /**
     * 被邀请用户 QQ 号
     */
    @SerialName("target_user_id")
    public val targetUserId: Long,
    /**
     * 请求状态
     */
    public val state: MilkyRawRequestState,
    /**
     * 处理请求的管理员 QQ 号
     */
    @SerialName("operator_id")
    public val operatorId: Long? = null,
) : MilkyRawGroupNotification() {
    public companion object {
        public const val TYPE: String = "invited_join_request"
    }
}
