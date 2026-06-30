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
import love.forte.simbot.milky.model.api.file.MilkyGetGroupFileDownloadUrlParam
import love.forte.simbot.milky.model.api.file.MilkyGetGroupFileDownloadUrlResponse
import kotlin.jvm.JvmStatic

/**
 * [get_group_file_download_url 获取群文件下载链接](https://milky.ntqqrev.org/api/file#get_group_file_download_url)
 *
 * @author Forte Scarlet
 */
public class MilkyGetGroupFileDownloadUrlApi private constructor(
    private val param: MilkyGetGroupFileDownloadUrlParam
) :
    SerializationBasedTypedMilkyApi<MilkyGetGroupFileDownloadUrlResponse>(),
    MilkyFileApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetGroupFileDownloadUrlResponse>
        get() = MilkyGetGroupFileDownloadUrlResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetGroupFileDownloadUrlResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyGetGroupFileDownloadUrlParam.serializer(), param)

    override fun toString(): String {
        return "MilkyGetGroupFileDownloadUrlApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "get_group_file_download_url"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetGroupFileDownloadUrlResponse.serializer())

        /**
         * 使用 [MilkyGetGroupFileDownloadUrlParam] 构建 [MilkyGetGroupFileDownloadUrlApi]。
         */
        @JvmStatic
        public fun create(param: MilkyGetGroupFileDownloadUrlParam): MilkyGetGroupFileDownloadUrlApi =
            MilkyGetGroupFileDownloadUrlApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyGetGroupFileDownloadUrlApi]。
         * @param groupId 群号
         * @param fileId 文件 ID
         */
        @JvmStatic
        public fun create(groupId: Long, fileId: String): MilkyGetGroupFileDownloadUrlApi =
            create(MilkyGetGroupFileDownloadUrlParam(groupId, fileId))
    }
}
