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

package love.forte.simbot.milky.model.api.system

import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.api.MilkyApiModel
import love.forte.simbot.milky.model.api.MilkyApiModelConstructor

/**
 * [get_login_info 获取登录信息](https://milky.ntqqrev.org/api/system#get_login_info)
 * API 的响应体结构模型（输出参数）。
 *
 * @author Forte Scarlet
 */
@Serializable
public data class MilkyGetLoginInfoResponse
@MilkyApiModelConstructor constructor(
    /**
     * 登录 QQ 号
     */
    val uin: Long,
    /**
     * 登录 QQ 昵称
     */
    val nickname: String,
) : MilkyApiModel
