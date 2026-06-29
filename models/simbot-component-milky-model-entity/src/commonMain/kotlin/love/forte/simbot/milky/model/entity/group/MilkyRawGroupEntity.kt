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
 * [GroupEntity 群实体](https://milky.ntqqrev.org/struct/GroupEntity).
 */
@Serializable
public data class MilkyRawGroupEntity
@MilkyEntityModelConstructor
internal constructor(
    /**
     * 群号
     */
    @SerialName("group_id")
    public val groupId: Long,
    /**
     * 群名称
     */
    @SerialName("group_name")
    public val groupName: String,
    /**
     * 群成员数量
     */
    @SerialName("member_count")
    public val memberCount: Int,
    /**
     * 群容量
     */
    @SerialName("max_member_count")
    public val maxMemberCount: Int,
    /**
     * 群备注
     */
    public val remark: String,
    /**
     * 群创建时间，Unix 时间戳（秒）
     */
    @SerialName("created_time")
    public val createdTime: Long,
    /**
     * 群简介
     */
    public val description: String,
    /**
     * 加群验证问题
     */
    public val question: String,
    /**
     * 群公告预览
     */
    public val announcement: String,
)
