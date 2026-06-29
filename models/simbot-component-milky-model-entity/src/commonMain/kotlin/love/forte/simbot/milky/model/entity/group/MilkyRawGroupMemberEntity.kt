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
import love.forte.simbot.milky.model.entity.MilkyRawUserSex

/**
 * [GroupMemberEntity 群成员实体](https://milky.ntqqrev.org/struct/GroupMemberEntity).
 */
@Serializable
public data class MilkyRawGroupMemberEntity
@MilkyEntityModelConstructor
internal constructor(
    /**
     * 用户 QQ 号
     */
    @SerialName("user_id")
    public val userId: Long,
    /**
     * 用户昵称
     */
    public val nickname: String,
    /**
     * 用户性别
     */
    public val sex: MilkyRawUserSex,
    /**
     * 群号
     */
    @SerialName("group_id")
    public val groupId: Long,
    /**
     * 成员备注
     */
    public val card: String,
    /**
     * 专属头衔
     */
    public val title: String,
    /**
     * 群等级，注意和 QQ 等级区分
     */
    public val level: Int,
    /**
     * 权限等级
     */
    public val role: MilkyRawGroupMemberRole,
    /**
     * 入群时间，Unix 时间戳（秒）
     */
    @SerialName("join_time")
    public val joinTime: Long,
    /**
     * 最后发言时间，Unix 时间戳（秒）
     */
    @SerialName("last_sent_time")
    public val lastSentTime: Long,
    /**
     * 禁言结束时间，Unix 时间戳（秒）
     */
    @SerialName("shut_up_end_time")
    public val shutUpEndTime: Long? = null,
)
