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

package love.forte.simbot.milky.api.file

import love.forte.simbot.milky.api.MilkyApi
import love.forte.simbot.milky.api.UnitResultMilkyApi
import love.forte.simbot.milky.model.api.file.MilkyRenameGroupFileParam
import kotlin.jvm.JvmStatic

/**
 * [rename_group_file 重命名群文件](https://milky.ntqqrev.org/api/file#rename_group_file)
 *
 * @author Forte Scarlet
 */
public class MilkyRenameGroupFileApi private constructor(
    private val param: MilkyRenameGroupFileParam
) : UnitResultMilkyApi(), MilkyFileApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyRenameGroupFileParam.serializer(), param)

    override fun toString(): String {
        return "MilkyRenameGroupFileApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "rename_group_file"

        /**
         * 使用 [MilkyRenameGroupFileParam] 构建 [MilkyRenameGroupFileApi]。
         */
        @JvmStatic
        public fun create(param: MilkyRenameGroupFileParam): MilkyRenameGroupFileApi = MilkyRenameGroupFileApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyRenameGroupFileApi]。
         * @param groupId 群号
         * @param fileId 文件 ID
         * @param parentFolderId 文件所在的文件夹 ID
         * @param newFileName 新文件名称
         */
        @JvmStatic
        public fun create(
            groupId: Long,
            fileId: String,
            parentFolderId: String = "/",
            newFileName: String
        ): MilkyRenameGroupFileApi = create(MilkyRenameGroupFileParam(groupId, fileId, parentFolderId, newFileName))
    }
}
