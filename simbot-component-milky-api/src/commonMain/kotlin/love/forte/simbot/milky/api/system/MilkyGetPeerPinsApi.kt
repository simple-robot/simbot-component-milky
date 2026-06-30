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
import love.forte.simbot.milky.api.SerializationBasedTypedMilkyApi
import love.forte.simbot.milky.model.api.MilkyApiResult
import love.forte.simbot.milky.model.api.system.MilkyGetPeerPinsResponse
import kotlin.jvm.JvmStatic

/**
 * [get_peer_pins 获取置顶的好友和群列表](https://milky.ntqqrev.org/api/system#get_peer_pins)
 *
 * @author Forte Scarlet
 */
public class MilkyGetPeerPinsApi private constructor() :
    SerializationBasedTypedMilkyApi<MilkyGetPeerPinsResponse>(),
    MilkySystemApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetPeerPinsResponse>
        get() = MilkyGetPeerPinsResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetPeerPinsResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String? = null
    override fun toString(): String {
        return "MilkyGetPeerPinsApi(param={})"
    }

    public companion object {
        public const val API_NAME: String = "get_peer_pins"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetPeerPinsResponse.serializer())
        private val INSTANCE = MilkyGetPeerPinsApi()

        /**
         * 获取 [MilkyGetPeerPinsApi] 实例。
         */
        @JvmStatic
        public fun instance(): MilkyGetPeerPinsApi = INSTANCE
    }
}
