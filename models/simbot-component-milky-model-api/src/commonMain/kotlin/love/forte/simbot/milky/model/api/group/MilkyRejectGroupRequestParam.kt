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

package love.forte.simbot.milky.model.api.group

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.api.MilkyApiParamModel

/**
 * [reject_group_request 拒绝入群/邀请他人入群请求](https://milky.ntqqrev.org/api/group#reject_group_request)
 * API 的请求入参。
 */
@Serializable
public class MilkyRejectGroupRequestParam(
    /**
     * 请求对应的通知序列号
     */
    @SerialName("notification_seq")
    public val notificationSeq: Long,
    /**
     * 请求对应的通知类型
     */
    @SerialName("notification_type")
    public val notificationType: MilkyGroupRequestNotificationType,
    /**
     * 请求所在的群号
     */
    @SerialName("group_id")
    public val groupId: Long,
    /**
     * 是否是被过滤的请求
     */
    @SerialName("is_filtered")
    public val isFiltered: Boolean = false,
    /**
     * 拒绝理由
     */
    public val reason: String? = null,
) : MilkyApiParamModel {
    override fun toString(): String {
        return "MilkyRejectGroupRequestParam(notificationSeq=$notificationSeq, notificationType=$notificationType, groupId=$groupId, isFiltered=$isFiltered, reason='$reason')"
    }
}
