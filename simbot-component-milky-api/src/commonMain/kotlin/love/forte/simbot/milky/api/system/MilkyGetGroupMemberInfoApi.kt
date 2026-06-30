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
import love.forte.simbot.milky.model.api.system.MilkyGetGroupMemberInfoParam
import love.forte.simbot.milky.model.api.system.MilkyGetGroupMemberInfoResponse
import kotlin.jvm.JvmStatic

/**
 * [get_group_member_info 获取群成员信息](https://milky.ntqqrev.org/api/system#get_group_member_info)
 *
 * @author Forte Scarlet
 */
public class MilkyGetGroupMemberInfoApi private constructor(
    private val param: MilkyGetGroupMemberInfoParam
) :
    SerializationBasedTypedMilkyApi<MilkyGetGroupMemberInfoResponse>(),
    MilkySystemApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetGroupMemberInfoResponse>
        get() = MilkyGetGroupMemberInfoResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetGroupMemberInfoResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyGetGroupMemberInfoParam.serializer(), param)

    override fun toString(): String {
        return "MilkyGetGroupMemberInfoApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "get_group_member_info"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetGroupMemberInfoResponse.serializer())

        /**
         * 使用 [MilkyGetGroupMemberInfoParam] 构建 [MilkyGetGroupMemberInfoApi]。
         */
        @JvmStatic
        public fun create(param: MilkyGetGroupMemberInfoParam): MilkyGetGroupMemberInfoApi =
            MilkyGetGroupMemberInfoApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyGetGroupMemberInfoApi]。
         * @param groupId 群号
         * @param userId 群成员 QQ 号
         * @param noCache 是否强制不使用缓存
         */
        @JvmStatic
        public fun create(groupId: Long, userId: Long, noCache: Boolean = false): MilkyGetGroupMemberInfoApi =
            create(MilkyGetGroupMemberInfoParam(groupId, userId, noCache))
    }
}
