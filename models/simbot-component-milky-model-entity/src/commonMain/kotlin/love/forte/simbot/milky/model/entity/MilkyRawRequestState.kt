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

package love.forte.simbot.milky.model.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Milky 请求状态枚举。
 */
@Serializable
public enum class MilkyRawRequestState {
    /**
     * 待处理
     */
    @SerialName("pending")
    PENDING,

    /**
     * 已同意
     */
    @SerialName("accepted")
    ACCEPTED,

    /**
     * 已拒绝
     */
    @SerialName("rejected")
    REJECTED,

    /**
     * 已忽略
     */
    @SerialName("ignored")
    IGNORED,
}
