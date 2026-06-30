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

import kotlinx.serialization.KSerializer
import love.forte.simbot.milky.api.MilkyApi
import love.forte.simbot.milky.api.SerializationBasedTypedMilkyApi
import love.forte.simbot.milky.model.api.MilkyApiResult
import love.forte.simbot.milky.model.api.file.MilkyCreateGroupFolderParam
import love.forte.simbot.milky.model.api.file.MilkyCreateGroupFolderResponse
import kotlin.jvm.JvmStatic

/**
 * [create_group_folder 创建群文件夹](https://milky.ntqqrev.org/api/file#create_group_folder)
 *
 * @author Forte Scarlet
 */
public class MilkyCreateGroupFolderApi private constructor(
    private val param: MilkyCreateGroupFolderParam
) :
    SerializationBasedTypedMilkyApi<MilkyCreateGroupFolderResponse>(),
    MilkyFileApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyCreateGroupFolderResponse>
        get() = MilkyCreateGroupFolderResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyCreateGroupFolderResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyCreateGroupFolderParam.serializer(), param)

    override fun toString(): String {
        return "MilkyCreateGroupFolderApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "create_group_folder"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyCreateGroupFolderResponse.serializer())

        /**
         * 使用 [MilkyCreateGroupFolderParam] 构建 [MilkyCreateGroupFolderApi]。
         */
        @JvmStatic
        public fun create(param: MilkyCreateGroupFolderParam): MilkyCreateGroupFolderApi =
            MilkyCreateGroupFolderApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyCreateGroupFolderApi]。
         * @param groupId 群号
         * @param folderName 文件夹名称
         */
        @JvmStatic
        public fun create(groupId: Long, folderName: String): MilkyCreateGroupFolderApi =
            create(MilkyCreateGroupFolderParam(groupId, folderName))
    }
}
