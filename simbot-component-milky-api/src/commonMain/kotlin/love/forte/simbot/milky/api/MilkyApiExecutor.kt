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

import io.ktor.client.*
import io.ktor.client.statement.*
import love.forte.simbot.milky.model.api.MilkyApiResult
import love.forte.simbot.suspendrunner.ST

/**
 * 一个可以提供用于执行 [MilkyApi] 必要内容的执行器。
 * 一个 Milky API 的执行器至少要能够稳定提供
 *
 * @author Forte Scarlet
 */
@ST
public interface MilkyApiExecutor {
    /**
     * Milky API 执行器所需的执行属性。
     */
    public val executionProperties: MilkyApiExecutionProperties

    /**
     * 用于访问 API 的客户端。
     */
    public val apiClient: HttpClient

    // TODO @ST
    // TODO 注释

    /**
     * @see executeApi
     */
    public suspend fun execute(api: MilkyApi): HttpResponse =
        apiClient.executeApi(api, executionProperties.baseUrl, executionProperties.accessToken)

    /**
     * @see executeApiText
     */
    public suspend fun executeText(api: MilkyApi): String =
        apiClient.executeApiText(api, executionProperties.baseUrl, executionProperties.accessToken)

    /**
     * @see executeApiResult
     */
    public suspend fun <R : Any> executeResult(api: TypedMilkyApi<R>): MilkyApiResult<R> =
        apiClient.executeApiResult(api, executionProperties.baseUrl, executionProperties.accessToken)

    /**
     * @see executeApiContent
     */
    public suspend fun <R : Any> executeContent(api: TypedMilkyApi<R>): R =
        apiClient.executeApiContent(api, executionProperties.baseUrl, executionProperties.accessToken)
}


