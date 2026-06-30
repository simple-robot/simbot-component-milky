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
import love.forte.simbot.milky.model.api.file.MilkyUploadGroupFileParam
import love.forte.simbot.milky.model.api.file.MilkyUploadGroupFileResponse
import kotlin.jvm.JvmStatic

/**
 * [upload_group_file 上传群文件](https://milky.ntqqrev.org/api/file#upload_group_file)
 *
 * @author Forte Scarlet
 */
public class MilkyUploadGroupFileApi private constructor(
    private val param: MilkyUploadGroupFileParam
) :
    SerializationBasedTypedMilkyApi<MilkyUploadGroupFileResponse>(),
    MilkyFileApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyUploadGroupFileResponse>
        get() = MilkyUploadGroupFileResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyUploadGroupFileResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyUploadGroupFileParam.serializer(), param)

    override fun toString(): String {
        return "MilkyUploadGroupFileApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "upload_group_file"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyUploadGroupFileResponse.serializer())

        /**
         * 使用 [MilkyUploadGroupFileParam] 构建 [MilkyUploadGroupFileApi]。
         */
        @JvmStatic
        public fun create(param: MilkyUploadGroupFileParam): MilkyUploadGroupFileApi = MilkyUploadGroupFileApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyUploadGroupFileApi]。
         * @param groupId 群号
         * @param parentFolderId 目标文件夹 ID
         * @param fileUri 文件 URI，支持 file://、http(s)://、base64:// 三种格式
         * @param fileName 文件名称
         */
        @JvmStatic
        public fun create(
            groupId: Long,
            parentFolderId: String = "/",
            fileUri: String,
            fileName: String
        ): MilkyUploadGroupFileApi = create(MilkyUploadGroupFileParam(groupId, parentFolderId, fileUri, fileName))
    }
}
