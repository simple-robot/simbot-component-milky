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

package love.forte.simbot.milky.api.group

import kotlinx.serialization.KSerializer
import love.forte.simbot.milky.api.MilkyApi
import love.forte.simbot.milky.api.SerializationBasedTypedMilkyApi
import love.forte.simbot.milky.model.api.MilkyApiResult
import love.forte.simbot.milky.model.api.group.MilkyGetGroupAnnouncementsParam
import love.forte.simbot.milky.model.api.group.MilkyGetGroupAnnouncementsResponse
import kotlin.jvm.JvmStatic

/**
 * [get_group_announcements 获取群公告列表](https://milky.ntqqrev.org/api/group#get_group_announcements)
 *
 * @author Forte Scarlet
 */
public class MilkyGetGroupAnnouncementsApi private constructor(
    private val param: MilkyGetGroupAnnouncementsParam
) :
    SerializationBasedTypedMilkyApi<MilkyGetGroupAnnouncementsResponse>(),
    MilkyGroupApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetGroupAnnouncementsResponse>
        get() = MilkyGetGroupAnnouncementsResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetGroupAnnouncementsResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyGetGroupAnnouncementsParam.serializer(), param)

    override fun toString(): String {
        return "MilkyGetGroupAnnouncementsApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "get_group_announcements"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetGroupAnnouncementsResponse.serializer())

        /**
         * 使用 [MilkyGetGroupAnnouncementsParam] 构建 [MilkyGetGroupAnnouncementsApi]。
         */
        @JvmStatic
        public fun create(param: MilkyGetGroupAnnouncementsParam): MilkyGetGroupAnnouncementsApi =
            MilkyGetGroupAnnouncementsApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyGetGroupAnnouncementsApi]。
         * @param groupId 群号
         */
        @JvmStatic
        public fun create(groupId: Long): MilkyGetGroupAnnouncementsApi =
            create(MilkyGetGroupAnnouncementsParam(groupId))
    }
}
