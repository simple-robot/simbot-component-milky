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
import love.forte.simbot.milky.model.api.system.MilkyGetUserProfileParam
import love.forte.simbot.milky.model.api.system.MilkyGetUserProfileResponse
import kotlin.jvm.JvmStatic

/**
 * [get_user_profile 获取用户个人信息](https://milky.ntqqrev.org/api/system#get_user_profile)
 *
 * @author Forte Scarlet
 */
public class MilkyGetUserProfileApi private constructor(
    private val param: MilkyGetUserProfileParam
) :
    SerializationBasedTypedMilkyApi<MilkyGetUserProfileResponse>(),
    MilkySystemApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetUserProfileResponse>
        get() = MilkyGetUserProfileResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetUserProfileResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyGetUserProfileParam.serializer(), param)

    override fun toString(): String {
        return "MilkyGetUserProfileApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "get_user_profile"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetUserProfileResponse.serializer())

        /**
         * 使用 [MilkyGetUserProfileParam] 构建 [MilkyGetUserProfileApi]。
         */
        @JvmStatic
        public fun create(param: MilkyGetUserProfileParam): MilkyGetUserProfileApi = MilkyGetUserProfileApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyGetUserProfileApi]。
         * @param userId 用户 QQ 号
         */
        @JvmStatic
        public fun create(userId: Long): MilkyGetUserProfileApi = create(MilkyGetUserProfileParam(userId))
    }
}
