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

package love.forte.simbot.milky.api.system

import kotlinx.serialization.KSerializer
import love.forte.simbot.milky.api.MilkyApi
import love.forte.simbot.milky.api.SerializationBasedTypedMilkyApi
import love.forte.simbot.milky.model.api.MilkyApiResult
import love.forte.simbot.milky.model.api.system.MilkyGetGroupMemberListParam
import love.forte.simbot.milky.model.api.system.MilkyGetGroupMemberListResponse
import kotlin.jvm.JvmStatic

/**
 * [get_group_member_list 获取群成员列表](https://milky.ntqqrev.org/api/system#get_group_member_list)
 *
 * @author Forte Scarlet
 */
public class MilkyGetGroupMemberListApi private constructor(
    private val param: MilkyGetGroupMemberListParam
) :
    SerializationBasedTypedMilkyApi<MilkyGetGroupMemberListResponse>(),
    MilkySystemApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetGroupMemberListResponse>
        get() = MilkyGetGroupMemberListResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetGroupMemberListResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyGetGroupMemberListParam.serializer(), param)

    override fun toString(): String {
        return "MilkyGetGroupMemberListApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "get_group_member_list"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetGroupMemberListResponse.serializer())

        /**
         * 使用 [MilkyGetGroupMemberListParam] 构建 [MilkyGetGroupMemberListApi]。
         */
        @JvmStatic
        public fun create(param: MilkyGetGroupMemberListParam): MilkyGetGroupMemberListApi =
            MilkyGetGroupMemberListApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyGetGroupMemberListApi]。
         * @param groupId 群号
         * @param noCache 是否强制不使用缓存
         */
        @JvmStatic
        public fun create(groupId: Long, noCache: Boolean = false): MilkyGetGroupMemberListApi =
            create(MilkyGetGroupMemberListParam(groupId, noCache))
    }
}
