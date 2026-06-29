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
 * [GroupFileEntity 群文件实体](https://milky.ntqqrev.org/struct/GroupFileEntity).
 */
@Serializable
public data class MilkyRawGroupFileEntity
@MilkyEntityModelConstructor
internal constructor(
    /**
     * 群号
     */
    @SerialName("group_id")
    public val groupId: Long,
    /**
     * 文件 ID
     */
    @SerialName("file_id")
    public val fileId: String,
    /**
     * 文件名称
     */
    @SerialName("file_name")
    public val fileName: String,
    /**
     * 父文件夹 ID
     */
    @SerialName("parent_folder_id")
    public val parentFolderId: String,
    /**
     * 文件大小（字节）
     */
    @SerialName("file_size")
    public val fileSize: Long,
    /**
     * 上传时的 Unix 时间戳（秒）
     */
    @SerialName("uploaded_time")
    public val uploadedTime: Long,
    /**
     * 过期时的 Unix 时间戳（秒）
     */
    @SerialName("expire_time")
    public val expireTime: Long? = null,
    /**
     * 上传者 QQ 号
     */
    @SerialName("uploader_id")
    public val uploaderId: Long,
    /**
     * 下载次数
     */
    @SerialName("downloaded_times")
    public val downloadedTimes: Int,
)
