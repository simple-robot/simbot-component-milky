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

import love.forte.simbot.milky.api.MilkyApi
import love.forte.simbot.milky.api.UnitResultMilkyApi
import love.forte.simbot.milky.model.api.message.MilkyMessageScene
import love.forte.simbot.milky.model.api.system.MilkySetPeerPinParam
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

/**
 * [set_peer_pin 设置好友或群的置顶状态](https://milky.ntqqrev.org/api/system#set_peer_pin)
 *
 * @author Forte Scarlet
 */
public class MilkySetPeerPinApi private constructor(
    private val param: MilkySetPeerPinParam
) : UnitResultMilkyApi(), MilkySystemApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkySetPeerPinParam.serializer(), param)

    override fun toString(): String {
        return "MilkySetPeerPinApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "set_peer_pin"

        /**
         * 使用 [MilkySetPeerPinParam] 构建 [MilkySetPeerPinApi]。
         */
        @JvmStatic
        public fun create(param: MilkySetPeerPinParam): MilkySetPeerPinApi = MilkySetPeerPinApi(param)

        /**
         * 使用 API 入参字段构建 [MilkySetPeerPinApi]。
         * @param messageScene 要设置的会话的消息场景
         * @param peerId 要设置的好友 QQ 号或群号
         * @param isPinned 是否置顶，false 表示取消置顶
         */
        @JvmStatic
        @JvmOverloads
        public fun create(messageScene: MilkyMessageScene, peerId: Long, isPinned: Boolean = true): MilkySetPeerPinApi =
            create(MilkySetPeerPinParam(messageScene, peerId, isPinned))
    }
}
