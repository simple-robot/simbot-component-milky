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

package love.forte.simbot.milky.api

import love.forte.simbot.milky.model.api.MilkyApiResult

/**
 * 一个 Milky 的 API。API 总是基于 HTTP 协议与 JSON 格式交互的。
 * 在 [MilkyApi] 的基础上，对出参进行类型范型约束的子类型。
 *
 * @see MilkyApi
 * @param R 响应结果类型。
 * @author Forte Scarlet
 */
public interface TypedMilkyApi<R : Any> : MilkyApi {
    /**
     * 将返回值的 JSON 解析为目标的预期类型 [MilkyApiResult]。
     *
     * @see MilkyApiResult
     * @param resultJson 返回值的 JSON 字符串。
     */
    public fun decodeResult(resultJson: String): MilkyApiResult<R>

    /**
     * 将返回值内的 `data` 数据的 JSON 解析为目标的预期类型 [R]。
     *
     * @see MilkyApiResult.data
     * @param resultContentJson 返回值内的 `data` 数据的 JSON 字符串。
     */
    public fun decodeResultContent(resultContentJson: String): R

}
