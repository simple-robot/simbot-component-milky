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
 * [move_group_file 移动群文件](https://milky.ntqqrev.org/api/file#move_group_file)
 * API 的请求入参。
 */
@Serializable
public class MilkyMoveGroupFileParam(
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
     * 文件所在的文件夹 ID
     */
    @SerialName("parent_folder_id")
    public val parentFolderId: String = "/",
    /**
     * 目标文件夹 ID
     */
    @SerialName("target_folder_id")
    public val targetFolderId: String = "/",
) : MilkyApiParamModel {
    override fun toString(): String {
        return "MilkyMoveGroupFileParam(groupId=$groupId, fileId='$fileId', parentFolderId='$parentFolderId', targetFolderId='$targetFolderId')"
    }
}
