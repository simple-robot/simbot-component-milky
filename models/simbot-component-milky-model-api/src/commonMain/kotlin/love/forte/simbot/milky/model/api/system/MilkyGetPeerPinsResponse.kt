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

package love.forte.simbot.milky.model.api.system

import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.api.MilkyApiModelConstructor
import love.forte.simbot.milky.model.api.MilkyApiResponseModel
import love.forte.simbot.milky.model.entity.friend.MilkyRawFriendEntity
import love.forte.simbot.milky.model.entity.group.MilkyRawGroupEntity

/**
 * [get_peer_pins 获取置顶的好友和群列表](https://milky.ntqqrev.org/api/system#get_peer_pins)
 * API 的响应体结构模型（输出参数）。
 */
@Serializable
public data class MilkyGetPeerPinsResponse
@MilkyApiModelConstructor private constructor(
    /**
     * 置顶的好友列表
     */
    val friends: List<MilkyRawFriendEntity>,
    /**
     * 置顶的群列表
     */
    val groups: List<MilkyRawGroupEntity>,
) : MilkyApiResponseModel
