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
import love.forte.simbot.milky.model.api.file.MilkyMoveGroupFileParam
import kotlin.jvm.JvmStatic

/**
 * [move_group_file 移动群文件](https://milky.ntqqrev.org/api/file#move_group_file)
 *
 * @author Forte Scarlet
 */
public class MilkyMoveGroupFileApi private constructor(
    private val param: MilkyMoveGroupFileParam
) : UnitResultMilkyApi(), MilkyFileApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyMoveGroupFileParam.serializer(), param)

    override fun toString(): String {
        return "MilkyMoveGroupFileApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "move_group_file"

        /**
         * 使用 [MilkyMoveGroupFileParam] 构建 [MilkyMoveGroupFileApi]。
         */
        @JvmStatic
        public fun create(param: MilkyMoveGroupFileParam): MilkyMoveGroupFileApi = MilkyMoveGroupFileApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyMoveGroupFileApi]。
         * @param groupId 群号
         * @param fileId 文件 ID
         * @param parentFolderId 文件所在的文件夹 ID
         * @param targetFolderId 目标文件夹 ID
         */
        @JvmStatic
        public fun create(
            groupId: Long,
            fileId: String,
            parentFolderId: String = "/",
            targetFolderId: String = "/"
        ): MilkyMoveGroupFileApi = create(MilkyMoveGroupFileParam(groupId, fileId, parentFolderId, targetFolderId))
    }
}
