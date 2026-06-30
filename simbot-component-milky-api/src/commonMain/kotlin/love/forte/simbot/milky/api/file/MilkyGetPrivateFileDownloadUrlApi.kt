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
import love.forte.simbot.milky.model.api.file.MilkyGetPrivateFileDownloadUrlParam
import love.forte.simbot.milky.model.api.file.MilkyGetPrivateFileDownloadUrlResponse
import kotlin.jvm.JvmStatic

/**
 * [get_private_file_download_url 获取私聊文件下载链接](https://milky.ntqqrev.org/api/file#get_private_file_download_url)
 *
 * @author Forte Scarlet
 */
public class MilkyGetPrivateFileDownloadUrlApi private constructor(
    private val param: MilkyGetPrivateFileDownloadUrlParam
) :
    SerializationBasedTypedMilkyApi<MilkyGetPrivateFileDownloadUrlResponse>(),
    MilkyFileApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetPrivateFileDownloadUrlResponse>
        get() = MilkyGetPrivateFileDownloadUrlResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetPrivateFileDownloadUrlResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyGetPrivateFileDownloadUrlParam.serializer(), param)

    override fun toString(): String {
        return "MilkyGetPrivateFileDownloadUrlApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "get_private_file_download_url"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetPrivateFileDownloadUrlResponse.serializer())

        /**
         * 使用 [MilkyGetPrivateFileDownloadUrlParam] 构建 [MilkyGetPrivateFileDownloadUrlApi]。
         */
        @JvmStatic
        public fun create(param: MilkyGetPrivateFileDownloadUrlParam): MilkyGetPrivateFileDownloadUrlApi =
            MilkyGetPrivateFileDownloadUrlApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyGetPrivateFileDownloadUrlApi]。
         * @param userId 好友 QQ 号
         * @param fileId 文件 ID
         * @param fileHash 文件的 TriSHA1 哈希值
         */
        @JvmStatic
        public fun create(userId: Long, fileId: String, fileHash: String): MilkyGetPrivateFileDownloadUrlApi =
            create(MilkyGetPrivateFileDownloadUrlParam(userId, fileId, fileHash))
    }
}
