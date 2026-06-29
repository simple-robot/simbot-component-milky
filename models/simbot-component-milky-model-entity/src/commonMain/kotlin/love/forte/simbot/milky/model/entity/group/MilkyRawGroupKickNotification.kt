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

/**
 * [kick 群成员被移除通知](https://milky.ntqqrev.org/struct/GroupNotification#type-kick).
 */
@Serializable
@SerialName(MilkyRawGroupKickNotification.TYPE)
public data class MilkyRawGroupKickNotification
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
     * 被移除用户 QQ 号
     */
    @SerialName("target_user_id")
    public val targetUserId: Long,
    /**
     * 移除用户的管理员 QQ 号
     */
    @SerialName("operator_id")
    public val operatorId: Long,
) : MilkyRawGroupNotification() {
    public companion object {
        public const val TYPE: String = "kick"
    }
}
