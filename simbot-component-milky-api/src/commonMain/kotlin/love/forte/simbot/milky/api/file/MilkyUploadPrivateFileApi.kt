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
import love.forte.simbot.milky.model.api.file.MilkyUploadPrivateFileParam
import love.forte.simbot.milky.model.api.file.MilkyUploadPrivateFileResponse
import kotlin.jvm.JvmStatic

/**
 * [upload_private_file 上传私聊文件](https://milky.ntqqrev.org/api/file#upload_private_file)
 *
 * @author Forte Scarlet
 */
public class MilkyUploadPrivateFileApi private constructor(
    private val param: MilkyUploadPrivateFileParam
) :
    SerializationBasedTypedMilkyApi<MilkyUploadPrivateFileResponse>(),
    MilkyFileApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyUploadPrivateFileResponse>
        get() = MilkyUploadPrivateFileResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyUploadPrivateFileResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyUploadPrivateFileParam.serializer(), param)

    override fun toString(): String {
        return "MilkyUploadPrivateFileApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "upload_private_file"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyUploadPrivateFileResponse.serializer())

        /**
         * 使用 [MilkyUploadPrivateFileParam] 构建 [MilkyUploadPrivateFileApi]。
         */
        @JvmStatic
        public fun create(param: MilkyUploadPrivateFileParam): MilkyUploadPrivateFileApi =
            MilkyUploadPrivateFileApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyUploadPrivateFileApi]。
         * @param userId 好友 QQ 号
         * @param fileUri 文件 URI，支持 `file://`、`http(s)://`、`base64://` 三种格式
         * @param fileName 文件名称
         */
        @JvmStatic
        public fun create(userId: Long, fileUri: String, fileName: String): MilkyUploadPrivateFileApi =
            create(MilkyUploadPrivateFileParam(userId, fileUri, fileName))
    }
}
