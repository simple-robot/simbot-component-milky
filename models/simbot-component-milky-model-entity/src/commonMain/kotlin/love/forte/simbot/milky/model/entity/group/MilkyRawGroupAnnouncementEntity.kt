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
 * [GroupAnnouncementEntity 群公告实体](https://milky.ntqqrev.org/struct/GroupAnnouncementEntity).
 */
@Serializable
public data class MilkyRawGroupAnnouncementEntity
@MilkyEntityModelConstructor
internal constructor(
    /**
     * 群号
     */
    @SerialName("group_id")
    public val groupId: Long,
    /**
     * 公告 ID
     */
    @SerialName("announcement_id")
    public val announcementId: String,
    /**
     * 发送者 QQ 号
     */
    @SerialName("user_id")
    public val userId: Long,
    /**
     * Unix 时间戳（秒）
     */
    public val time: Long,
    /**
     * 公告内容
     */
    public val content: String,
    /**
     * 公告图片 URL
     */
    @SerialName("image_url")
    public val imageUrl: String? = null,
)
