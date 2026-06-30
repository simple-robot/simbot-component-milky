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

package love.forte.simbot.milky.model.api.file

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.api.MilkyApiParamModel

/**
 * [upload_group_file 上传群文件](https://milky.ntqqrev.org/api/file#upload_group_file)
 * API 的请求入参。
 */
@Serializable
public class MilkyUploadGroupFileParam(
    /**
     * 群号
     */
    @SerialName("group_id")
    public val groupId: Long,
    /**
     * 目标文件夹 ID
     */
    @SerialName("parent_folder_id")
    public val parentFolderId: String = "/",
    /**
     * 文件 URI，支持 file://、http(s)://、base64:// 三种格式
     */
    @SerialName("file_uri")
    public val fileUri: String,
    /**
     * 文件名称
     */
    @SerialName("file_name")
    public val fileName: String,
) : MilkyApiParamModel {
    override fun toString(): String {
        return "MilkyUploadGroupFileParam(groupId=$groupId, parentFolderId='$parentFolderId', fileUri='$fileUri', fileName='$fileName')"
    }
}
