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

package love.forte.simbot.component.milky.bot

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.http.*
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.overwriteWith

/**
 *  Milky Bot 的配置信息。
 * @author Forte Scarlet
 */
public class MilkyBotConfiguration {
    /**
     * 访问 API 的基础 URL（包含前缀），例如 `http://localhost:3000/api`。
     */
    public lateinit var apiServerHost: Url

    /**
     * 访问 event 的基础 URL（包含前缀），例如 `http://localhost:3000/event`。
     *
     * 如果 [protocol][Url.protocol] 为 `http` 或 `https` (例如 `http://localhost:3000/event`) ，则视为 SSE 连接。
     * 否则如果为 `ws` 或 `wss` (例如 `ws://localhost:3000/event`) ，则视为 WebSocket 连接。
     * 只接受上述两类四种 protocol 的值，否则会在校验阶段抛出 [IllegalArgumentException]。
     */
    public lateinit var eventServerHost: Url

    /**
     * 如果配置了 access token，则在访问服务端的 [apiServerHost] 时会携带此 token。
     */
    public var apiAccessToken: String? = null

    /**
     * 如果配置了 access token，则在访问服务端的 [eventServerHost] 时会携带此 token。
     */
    public var eventAccessToken: String? = null

    /**
     * 设置 [apiAccessToken] 和 [eventAccessToken] 为指定的值 [value]。
     */
    public fun accessToken(value: String?) {
        apiAccessToken = value
        eventAccessToken = value
    }

    /**
     * **额外的**序列化模块信息。
     * 如果不为 `null`，则内部使用的序列化器会
     * [overwriteWith][SerializersModule.overwriteWith] 此 [serializersModule]。
     *
     */
    public var serializersModule: SerializersModule? = null


    /**
     * 用于API请求的 [HttpClient] 所使用的[引擎](https://ktor.io/docs/http-client-engines.html)。
     *
     * 如果 [apiClientEngine] 和 [apiClientEngineFactory] 都为null，则会尝试使用一个默认引擎。
     *
     */
    public var apiClientEngine: HttpClientEngine? = null

    /**
     * 用于API请求的 [HttpClient] 所使用的[引擎](https://ktor.io/docs/http-client-engines.html) 工厂。
     *
     * 如果 [apiClientEngine] 和 [apiClientEngineFactory] 都为null，则会尝试使用一个默认引擎。
     *
     */
    public var apiClientEngineFactory: HttpClientEngineFactory<*>? = null

    /**
     * 用于 event 连接的 [HttpClient] 所使用的[引擎](https://ktor.io/docs/http-client-engines.html)。
     *
     * 如果 [eventClientEngine] 和 [eventClientEngineFactory] 都为null，则会尝试使用一个默认引擎。
     *
     * 选择引擎时注意根据自己的配置，选择支持 [SSE](https://ktor.io/docs/client-server-sent-events.html) 或
     * [WS](https://ktor.io/docs/client-websockets.html) 的引擎。
     *
     */
    public var eventClientEngine: HttpClientEngine? = null

    /**
     * 用于 event 连接的 [HttpClient] 所使用的[引擎](https://ktor.io/docs/http-client-engines.html) 工厂。
     *
     * 如果 [eventClientEngine] 和 [eventClientEngineFactory] 都为null，则会尝试使用一个默认引擎。
     *
     * 选择引擎时注意根据自己的配置，选择支持 [SSE](https://ktor.io/docs/client-server-sent-events.html) 或
     * [WS](https://ktor.io/docs/client-websockets.html) 的引擎。
     */
    public var eventClientEngineFactory: HttpClientEngineFactory<*>? = null


}
