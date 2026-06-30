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
 * [get_private_file_download_url 获取私聊文件下载链接](https://milky.ntqqrev.org/api/file#get_private_file_download_url)
 * API 的请求入参。
 */
@Serializable
public class MilkyGetPrivateFileDownloadUrlParam(
    /**
     * 好友 QQ 号
     */
    @SerialName("user_id")
    public val userId: Long,
    /**
     * 文件 ID
     */
    @SerialName("file_id")
    public val fileId: String,
    /**
     * 文件的 TriSHA1 哈希值
     */
    @SerialName("file_hash")
    public val fileHash: String,
) : MilkyApiParamModel {
    override fun toString(): String {
        return "MilkyGetPrivateFileDownloadUrlParam(userId=$userId, fileId='$fileId', fileHash='$fileHash')"
    }
}
