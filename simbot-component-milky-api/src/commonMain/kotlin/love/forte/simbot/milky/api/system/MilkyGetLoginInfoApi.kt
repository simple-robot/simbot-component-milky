/*
 *     Copyright (c) 2026. ForteScarlet.
 *
 *     Project    https://github.com/simple-robot/simpler-robot
 *     Email      ForteScarlet@163.com
 *
 *     This file is part of the Simple Robot Library (Alias: simple-robot, simbot, etc.).
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
 *     You should have received a copy of the Lesser GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package love.forte.simbot.milky.api.system

import kotlinx.serialization.KSerializer
import love.forte.simbot.milky.api.SerializationBasedTypedMilkyApi
import love.forte.simbot.milky.model.api.MilkyApiResult
import love.forte.simbot.milky.model.api.system.MilkyGetLoginInfoResponse

/**
 * [get_login_info 获取登录信息](https://milky.ntqqrev.org/api/system#get_login_info)
 *
 * @author Forte Scarlet
 */
public class MilkyGetLoginInfoApi : SerializationBasedTypedMilkyApi<MilkyGetLoginInfoResponse>() {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetLoginInfoResponse>
        get() = MilkyGetLoginInfoResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetLoginInfoResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String? = null

    public companion object {
        public const val API_NAME: String = "get_login_info"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetLoginInfoResponse.serializer())
    }
}
