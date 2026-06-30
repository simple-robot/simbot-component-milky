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
import love.forte.simbot.milky.model.api.file.MilkyGetGroupFilesParam
import love.forte.simbot.milky.model.api.file.MilkyGetGroupFilesResponse
import kotlin.jvm.JvmStatic

/**
 * [get_group_files 获取群文件列表](https://milky.ntqqrev.org/api/file#get_group_files)
 *
 * @author Forte Scarlet
 */
public class MilkyGetGroupFilesApi private constructor(
    private val param: MilkyGetGroupFilesParam
) :
    SerializationBasedTypedMilkyApi<MilkyGetGroupFilesResponse>(),
    MilkyFileApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetGroupFilesResponse>
        get() = MilkyGetGroupFilesResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetGroupFilesResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyGetGroupFilesParam.serializer(), param)

    override fun toString(): String {
        return "MilkyGetGroupFilesApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "get_group_files"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetGroupFilesResponse.serializer())

        /**
         * 使用 [MilkyGetGroupFilesParam] 构建 [MilkyGetGroupFilesApi]。
         */
        @JvmStatic
        public fun create(param: MilkyGetGroupFilesParam): MilkyGetGroupFilesApi = MilkyGetGroupFilesApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyGetGroupFilesApi]。
         * @param groupId 群号
         * @param parentFolderId 父文件夹 ID
         */
        @JvmStatic
        public fun create(groupId: Long, parentFolderId: String = "/"): MilkyGetGroupFilesApi =
            create(MilkyGetGroupFilesParam(groupId, parentFolderId))
    }
}
