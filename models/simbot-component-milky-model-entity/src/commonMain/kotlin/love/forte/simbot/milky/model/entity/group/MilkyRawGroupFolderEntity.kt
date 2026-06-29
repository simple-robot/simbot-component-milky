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
 * [GroupFolderEntity 群文件夹实体](https://milky.ntqqrev.org/struct/GroupFolderEntity).
 */
@Serializable
public data class MilkyRawGroupFolderEntity
@MilkyEntityModelConstructor
internal constructor(
    /**
     * 群号
     */
    @SerialName("group_id")
    public val groupId: Long,
    /**
     * 文件夹 ID
     */
    @SerialName("folder_id")
    public val folderId: String,
    /**
     * 父文件夹 ID
     */
    @SerialName("parent_folder_id")
    public val parentFolderId: String,
    /**
     * 文件夹名称
     */
    @SerialName("folder_name")
    public val folderName: String,
    /**
     * 创建时的 Unix 时间戳（秒）
     */
    @SerialName("created_time")
    public val createdTime: Long,
    /**
     * 最后修改时的 Unix 时间戳（秒）
     */
    @SerialName("last_modified_time")
    public val lastModifiedTime: Long,
    /**
     * 创建者 QQ 号
     */
    @SerialName("creator_id")
    public val creatorId: Long,
    /**
     * 文件数量
     */
    @SerialName("file_count")
    public val fileCount: Int,
)
