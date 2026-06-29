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

package love.forte.simbot.milky.model.entity.friend

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.entity.MilkyEntityModelConstructor
import love.forte.simbot.milky.model.entity.MilkyRawRequestState

/**
 * [FriendRequest 好友请求实体](https://milky.ntqqrev.org/struct/FriendRequest).
 */
@Serializable
public data class MilkyRawFriendRequest
@MilkyEntityModelConstructor
internal constructor(
    /**
     * 请求发起时的 Unix 时间戳（秒）
     */
    public val time: Long,
    /**
     * 请求发起者 QQ 号
     */
    @SerialName("initiator_id")
    public val initiatorId: Long,
    /**
     * 请求发起者 UID
     */
    @SerialName("initiator_uid")
    public val initiatorUid: String,
    /**
     * 目标用户 QQ 号
     */
    @SerialName("target_user_id")
    public val targetUserId: Long,
    /**
     * 目标用户 UID
     */
    @SerialName("target_user_uid")
    public val targetUserUid: String,
    /**
     * 请求状态
     */
    public val state: MilkyRawRequestState,
    /**
     * 申请附加信息
     */
    public val comment: String,
    /**
     * 申请来源
     */
    public val via: String,
    /**
     * 请求是否被过滤（发起自风险账户）
     */
    @SerialName("is_filtered")
    public val isFiltered: Boolean,
)
