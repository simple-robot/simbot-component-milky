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
 * [get_group_notifications 获取群通知列表](https://milky.ntqqrev.org/api/group#get_group_notifications)
 * API 的请求入参。
 */
@Serializable
public class MilkyGetGroupNotificationsParam(
    /**
     * 起始通知序列号
     */
    @SerialName("start_notification_seq")
    public val startNotificationSeq: Long? = null,
    /**
     * true 表示只获取被过滤的通知，false 表示只获取未被过滤的通知
     */
    @SerialName("is_filtered")
    public val isFiltered: Boolean = false,
    /**
     * 获取的最大通知数量
     */
    public val limit: Int = 20,
) : MilkyApiParamModel {
    override fun toString(): String {
        return "MilkyGetGroupNotificationsParam(startNotificationSeq=$startNotificationSeq, isFiltered=$isFiltered, limit=$limit)"
    }
}
