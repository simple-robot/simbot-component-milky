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

@file:JvmName("MilkyApiExecutors")
@file:JvmMultifileClass

package love.forte.simbot.milky.api

import io.ktor.client.*
import io.ktor.client.statement.*
import io.ktor.http.*
import love.forte.simbot.annotations.Api4J
import love.forte.simbot.annotations.InternalSimbotAPI
import love.forte.simbot.milky.model.api.MilkyApiResult
import love.forte.simbot.milky.model.api.MilkyApiResultFailedStatusException
import love.forte.simbot.suspendrunner.asPublisher
import love.forte.simbot.suspendrunner.runInAsync
import love.forte.simbot.suspendrunner.runInNoScopeBlocking
import org.jetbrains.annotations.Blocking
import org.jetbrains.annotations.NonBlocking
import org.reactivestreams.Publisher
import java.util.concurrent.CompletableFuture

/**
 * 使用 [HttpClient] 执行 [api] 并得到响应结果 [HttpResponse]。
 *
 * 此函数是 [executeApi] 的 JVM 阻塞桥接。
 *
 * @param api 要被执行的 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 * @param validateStatus 是否校验 HTTP Status。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 [validateStatus] 为 `true` 且 HTTP Status 不在 `200..<300`。
 *
 * @see executeApi
 */
@Api4J
@Blocking
public fun HttpClient.executeApiBlocking(
    api: MilkyApi,
    baseUrl: String,
    accessToken: String? = null,
    validateStatus: Boolean = true,
): HttpResponse = runInNoScopeBlocking {
    executeApi(api, baseUrl, accessToken, validateStatus)
}

/**
 * 使用 [HttpClient] 执行 [api] 并得到响应结果 [HttpResponse]。
 *
 * 此函数是 [executeApi] 的 JVM 阻塞桥接。
 *
 * @param api 要被执行的 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 * @param validateStatus 是否校验 HTTP Status。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 [validateStatus] 为 `true` 且 HTTP Status 不在 `200..<300`。
 *
 * @see executeApi
 */
@Api4J
@Blocking
public fun HttpClient.executeApiBlocking(
    api: MilkyApi,
    baseUrl: Url,
    accessToken: String? = null,
    validateStatus: Boolean = true,
): HttpResponse = runInNoScopeBlocking {
    executeApi(api, baseUrl, accessToken, validateStatus)
}

/**
 * 使用 [HttpClient] 执行 [api] 并得到响应结果 [HttpResponse]。
 *
 * 此函数是 [executeApi] 的 JVM 异步桥接，返回 [CompletableFuture]。
 *
 * @param api 要被执行的 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 * @param validateStatus 是否校验 HTTP Status。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 [validateStatus] 为 `true` 且 HTTP Status 不在 `200..<300`。
 *
 * @see executeApi
 */
@OptIn(InternalSimbotAPI::class)
@Api4J
@NonBlocking
public fun HttpClient.executeApiAsync(
    api: MilkyApi,
    baseUrl: String,
    accessToken: String? = null,
    validateStatus: Boolean = true,
): CompletableFuture<out HttpResponse> = runInAsync {
    executeApi(api, baseUrl, accessToken, validateStatus)
}

/**
 * 使用 [HttpClient] 执行 [api] 并得到响应结果 [HttpResponse]。
 *
 * 此函数是 [executeApi] 的 JVM 异步桥接，返回 [CompletableFuture]。
 *
 * @param api 要被执行的 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 * @param validateStatus 是否校验 HTTP Status。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 [validateStatus] 为 `true` 且 HTTP Status 不在 `200..<300`。
 *
 * @see executeApi
 */
@OptIn(InternalSimbotAPI::class)
@Api4J
@NonBlocking
public fun HttpClient.executeApiAsync(
    api: MilkyApi,
    baseUrl: Url,
    accessToken: String? = null,
    validateStatus: Boolean = true,
): CompletableFuture<out HttpResponse> = runInAsync {
    executeApi(api, baseUrl, accessToken, validateStatus)
}

/**
 * 使用 [HttpClient] 执行 [api] 并得到响应结果 [HttpResponse]。
 *
 * 此函数是 [executeApi] 的 JVM Reactive 桥接，返回 Reactive Streams [Publisher]。
 * 可以在引入 [reactor-core](https://github.com/reactor/reactor-core) 后转为 `Mono`：
 *
 * ```kotlin
 * val mono = reactor.core.publisher.Mono.from(client.executeApiReactive(api, baseUrl))
 * ```
 *
 * 如果需要与 Kotlin 协程的 Reactor API 互操作，可另行引入
 * [kotlinx-coroutines-reactor](https://github.com/Kotlin/kotlinx.coroutines/tree/master/reactive/kotlinx-coroutines-reactor)。
 *
 * @param api 要被执行的 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 * @param validateStatus 是否校验 HTTP Status。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 [validateStatus] 为 `true` 且 HTTP Status 不在 `200..<300`。
 *
 * @see executeApi
 */
@OptIn(InternalSimbotAPI::class)
@Api4J
public fun HttpClient.executeApiReactive(
    api: MilkyApi,
    baseUrl: String,
    accessToken: String? = null,
    validateStatus: Boolean = true,
): Publisher<out HttpResponse> = asPublisher {
    executeApi(api, baseUrl, accessToken, validateStatus)
}

/**
 * 使用 [HttpClient] 执行 [api] 并得到响应结果 [HttpResponse]。
 *
 * 此函数是 [executeApi] 的 JVM Reactive 桥接，返回 Reactive Streams [Publisher]。
 * 可参考同名 `String` 重载中转为 `Mono` 的示例。
 *
 * @param api 要被执行的 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 * @param validateStatus 是否校验 HTTP Status。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 [validateStatus] 为 `true` 且 HTTP Status 不在 `200..<300`。
 *
 * @see executeApi
 */
@OptIn(InternalSimbotAPI::class)
@Api4J
public fun HttpClient.executeApiReactive(
    api: MilkyApi,
    baseUrl: Url,
    accessToken: String? = null,
    validateStatus: Boolean = true,
): Publisher<out HttpResponse> = asPublisher {
    executeApi(api, baseUrl, accessToken, validateStatus)
}

/**
 * 使用 [HttpClient] 执行 [api] 并将响应体读取为字符串。
 *
 * 此函数会固定启用 HTTP Status 校验。
 * 此函数是 [executeApiText] 的 JVM 阻塞桥接。
 *
 * @param api 要被执行的 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 *
 * @return 响应体文本。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 *
 * @see executeApiText
 */
@Api4J
@Blocking
public fun HttpClient.executeApiTextBlocking(
    api: MilkyApi,
    baseUrl: String,
    accessToken: String? = null,
): String = runInNoScopeBlocking {
    executeApiText(api, baseUrl, accessToken)
}

/**
 * 使用 [HttpClient] 执行 [api] 并将响应体读取为字符串。
 *
 * 此函数会固定启用 HTTP Status 校验。
 * 此函数是 [executeApiText] 的 JVM 阻塞桥接。
 *
 * @param api 要被执行的 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 *
 * @return 响应体文本。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 *
 * @see executeApiText
 */
@Api4J
@Blocking
public fun HttpClient.executeApiTextBlocking(
    api: MilkyApi,
    baseUrl: Url,
    accessToken: String? = null,
): String = runInNoScopeBlocking {
    executeApiText(api, baseUrl, accessToken)
}

/**
 * 使用 [HttpClient] 执行 [api] 并将响应体读取为字符串。
 *
 * 此函数会固定启用 HTTP Status 校验。
 * 此函数是 [executeApiText] 的 JVM 异步桥接，返回 [CompletableFuture]。
 *
 * @param api 要被执行的 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 *
 * @return 响应体文本。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 *
 * @see executeApiText
 */
@OptIn(InternalSimbotAPI::class)
@Api4J
@NonBlocking
public fun HttpClient.executeApiTextAsync(
    api: MilkyApi,
    baseUrl: String,
    accessToken: String? = null,
): CompletableFuture<out String> = runInAsync {
    executeApiText(api, baseUrl, accessToken)
}

/**
 * 使用 [HttpClient] 执行 [api] 并将响应体读取为字符串。
 *
 * 此函数会固定启用 HTTP Status 校验。
 * 此函数是 [executeApiText] 的 JVM 异步桥接，返回 [CompletableFuture]。
 *
 * @param api 要被执行的 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 *
 * @return 响应体文本。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 *
 * @see executeApiText
 */
@OptIn(InternalSimbotAPI::class)
@Api4J
@NonBlocking
public fun HttpClient.executeApiTextAsync(
    api: MilkyApi,
    baseUrl: Url,
    accessToken: String? = null,
): CompletableFuture<out String> = runInAsync {
    executeApiText(api, baseUrl, accessToken)
}

/**
 * 使用 [HttpClient] 执行 [api] 并将响应体读取为字符串。
 *
 * 此函数会固定启用 HTTP Status 校验。
 * 此函数是 [executeApiText] 的 JVM Reactive 桥接，返回 Reactive Streams [Publisher]。
 * 可以在引入 [reactor-core](https://github.com/reactor/reactor-core) 后转为 `Mono`：
 *
 * ```kotlin
 * val mono = reactor.core.publisher.Mono.from(client.executeApiTextReactive(api, baseUrl))
 * ```
 *
 * 如果需要与 Kotlin 协程的 Reactor API 互操作，可另行引入
 * [kotlinx-coroutines-reactor](https://github.com/Kotlin/kotlinx.coroutines/tree/master/reactive/kotlinx-coroutines-reactor)。
 *
 * @param api 要被执行的 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 *
 * @return 响应体文本。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 *
 * @see executeApiText
 */
@OptIn(InternalSimbotAPI::class)
@Api4J
public fun HttpClient.executeApiTextReactive(
    api: MilkyApi,
    baseUrl: String,
    accessToken: String? = null,
): Publisher<out String> = asPublisher {
    executeApiText(api, baseUrl, accessToken)
}

/**
 * 使用 [HttpClient] 执行 [api] 并将响应体读取为字符串。
 *
 * 此函数会固定启用 HTTP Status 校验。
 * 此函数是 [executeApiText] 的 JVM Reactive 桥接，返回 Reactive Streams [Publisher]。
 * 可参考同名 `String` 重载中转为 `Mono` 的示例。
 *
 * @param api 要被执行的 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 *
 * @return 响应体文本。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 *
 * @see executeApiText
 */
@OptIn(InternalSimbotAPI::class)
@Api4J
public fun HttpClient.executeApiTextReactive(
    api: MilkyApi,
    baseUrl: Url,
    accessToken: String? = null,
): Publisher<out String> = asPublisher {
    executeApiText(api, baseUrl, accessToken)
}

/**
 * 使用 [HttpClient] 执行 [api]，并将响应体解析为 Milky API 的统一返回结构 [MilkyApiResult]。
 *
 * 此函数会固定启用 HTTP Status 校验。若 [validateResult] 为 `true`，还会校验 Milky 返回结构中的
 * [MilkyApiResult.retcode] 是否为 [MilkyApiResult.SUCCESS_RETCODE]。
 * 此函数是 [executeApiResult] 的 JVM 阻塞桥接。
 *
 * @param R API 成功数据类型。
 * @param api 要被执行的有类型 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 * @param validateResult 是否校验 Milky API 返回体中的 [MilkyApiResult.retcode]。
 *
 * @return 解析后的 Milky API 统一返回结构。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 * @throws MilkyApiResultFailedStatusException 如果 [validateResult] 为 `true` 且 [MilkyApiResult.retcode] 不为
 * [MilkyApiResult.SUCCESS_RETCODE]。
 *
 * @see executeApiResult
 */
@Api4J
@Blocking
public fun <R : Any> HttpClient.executeApiResultBlocking(
    api: TypedMilkyApi<R>,
    baseUrl: String,
    accessToken: String? = null,
    validateResult: Boolean = true,
): MilkyApiResult<R> = runInNoScopeBlocking {
    executeApiResult(api, baseUrl, accessToken, validateResult)
}

/**
 * 使用 [HttpClient] 执行 [api]，并将响应体解析为 Milky API 的统一返回结构 [MilkyApiResult]。
 *
 * 此函数会固定启用 HTTP Status 校验。若 [validateResult] 为 `true`，还会校验 Milky 返回结构中的
 * [MilkyApiResult.retcode] 是否为 [MilkyApiResult.SUCCESS_RETCODE]。
 * 此函数是 [executeApiResult] 的 JVM 阻塞桥接。
 *
 * @param R API 成功数据类型。
 * @param api 要被执行的有类型 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 * @param validateResult 是否校验 Milky API 返回体中的 [MilkyApiResult.retcode]。
 *
 * @return 解析后的 Milky API 统一返回结构。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 * @throws MilkyApiResultFailedStatusException 如果 [validateResult] 为 `true` 且 [MilkyApiResult.retcode] 不为
 * [MilkyApiResult.SUCCESS_RETCODE]。
 *
 * @see executeApiResult
 */
@Api4J
@Blocking
public fun <R : Any> HttpClient.executeApiResultBlocking(
    api: TypedMilkyApi<R>,
    baseUrl: Url,
    accessToken: String? = null,
    validateResult: Boolean = true,
): MilkyApiResult<R> = runInNoScopeBlocking {
    executeApiResult(api, baseUrl, accessToken, validateResult)
}

/**
 * 使用 [HttpClient] 执行 [api]，并将响应体解析为 Milky API 的统一返回结构 [MilkyApiResult]。
 *
 * 此函数会固定启用 HTTP Status 校验。若 [validateResult] 为 `true`，还会校验 Milky 返回结构中的
 * [MilkyApiResult.retcode] 是否为 [MilkyApiResult.SUCCESS_RETCODE]。
 * 此函数是 [executeApiResult] 的 JVM 异步桥接，返回 [CompletableFuture]。
 *
 * @param R API 成功数据类型。
 * @param api 要被执行的有类型 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 * @param validateResult 是否校验 Milky API 返回体中的 [MilkyApiResult.retcode]。
 *
 * @return 解析后的 Milky API 统一返回结构。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 * @throws MilkyApiResultFailedStatusException 如果 [validateResult] 为 `true` 且 [MilkyApiResult.retcode] 不为
 * [MilkyApiResult.SUCCESS_RETCODE]。
 *
 * @see executeApiResult
 */
@OptIn(InternalSimbotAPI::class)
@Api4J
@NonBlocking
public fun <R : Any> HttpClient.executeApiResultAsync(
    api: TypedMilkyApi<R>,
    baseUrl: String,
    accessToken: String? = null,
    validateResult: Boolean = true,
): CompletableFuture<out MilkyApiResult<R>> = runInAsync {
    executeApiResult(api, baseUrl, accessToken, validateResult)
}

/**
 * 使用 [HttpClient] 执行 [api]，并将响应体解析为 Milky API 的统一返回结构 [MilkyApiResult]。
 *
 * 此函数会固定启用 HTTP Status 校验。若 [validateResult] 为 `true`，还会校验 Milky 返回结构中的
 * [MilkyApiResult.retcode] 是否为 [MilkyApiResult.SUCCESS_RETCODE]。
 * 此函数是 [executeApiResult] 的 JVM 异步桥接，返回 [CompletableFuture]。
 *
 * @param R API 成功数据类型。
 * @param api 要被执行的有类型 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 * @param validateResult 是否校验 Milky API 返回体中的 [MilkyApiResult.retcode]。
 *
 * @return 解析后的 Milky API 统一返回结构。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 * @throws MilkyApiResultFailedStatusException 如果 [validateResult] 为 `true` 且 [MilkyApiResult.retcode] 不为
 * [MilkyApiResult.SUCCESS_RETCODE]。
 *
 * @see executeApiResult
 */
@OptIn(InternalSimbotAPI::class)
@Api4J
@NonBlocking
public fun <R : Any> HttpClient.executeApiResultAsync(
    api: TypedMilkyApi<R>,
    baseUrl: Url,
    accessToken: String? = null,
    validateResult: Boolean = true,
): CompletableFuture<out MilkyApiResult<R>> = runInAsync {
    executeApiResult(api, baseUrl, accessToken, validateResult)
}

/**
 * 使用 [HttpClient] 执行 [api]，并将响应体解析为 Milky API 的统一返回结构 [MilkyApiResult]。
 *
 * 此函数会固定启用 HTTP Status 校验。若 [validateResult] 为 `true`，还会校验 Milky 返回结构中的
 * [MilkyApiResult.retcode] 是否为 [MilkyApiResult.SUCCESS_RETCODE]。
 * 此函数是 [executeApiResult] 的 JVM Reactive 桥接，返回 Reactive Streams [Publisher]。
 * 可以在引入 [reactor-core](https://github.com/reactor/reactor-core) 后转为 `Mono`：
 *
 * ```kotlin
 * val mono = reactor.core.publisher.Mono.from(client.executeApiResultReactive(api, baseUrl))
 * ```
 *
 * 如果需要与 Kotlin 协程的 Reactor API 互操作，可另行引入
 * [kotlinx-coroutines-reactor](https://github.com/Kotlin/kotlinx.coroutines/tree/master/reactive/kotlinx-coroutines-reactor)。
 *
 * @param R API 成功数据类型。
 * @param api 要被执行的有类型 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 * @param validateResult 是否校验 Milky API 返回体中的 [MilkyApiResult.retcode]。
 *
 * @return 解析后的 Milky API 统一返回结构。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 * @throws MilkyApiResultFailedStatusException 如果 [validateResult] 为 `true` 且 [MilkyApiResult.retcode] 不为
 * [MilkyApiResult.SUCCESS_RETCODE]。
 *
 * @see executeApiResult
 */
@OptIn(InternalSimbotAPI::class)
@Api4J
public fun <R : Any> HttpClient.executeApiResultReactive(
    api: TypedMilkyApi<R>,
    baseUrl: String,
    accessToken: String? = null,
    validateResult: Boolean = true,
): Publisher<out MilkyApiResult<R>> = asPublisher {
    executeApiResult(api, baseUrl, accessToken, validateResult)
}

/**
 * 使用 [HttpClient] 执行 [api]，并将响应体解析为 Milky API 的统一返回结构 [MilkyApiResult]。
 *
 * 此函数会固定启用 HTTP Status 校验。若 [validateResult] 为 `true`，还会校验 Milky 返回结构中的
 * [MilkyApiResult.retcode] 是否为 [MilkyApiResult.SUCCESS_RETCODE]。
 * 此函数是 [executeApiResult] 的 JVM Reactive 桥接，返回 Reactive Streams [Publisher]。
 * 可参考同名 `String` 重载中转为 `Mono` 的示例。
 *
 * @param R API 成功数据类型。
 * @param api 要被执行的有类型 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 * @param validateResult 是否校验 Milky API 返回体中的 [MilkyApiResult.retcode]。
 *
 * @return 解析后的 Milky API 统一返回结构。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 * @throws MilkyApiResultFailedStatusException 如果 [validateResult] 为 `true` 且 [MilkyApiResult.retcode] 不为
 * [MilkyApiResult.SUCCESS_RETCODE]。
 *
 * @see executeApiResult
 */
@OptIn(InternalSimbotAPI::class)
@Api4J
public fun <R : Any> HttpClient.executeApiResultReactive(
    api: TypedMilkyApi<R>,
    baseUrl: Url,
    accessToken: String? = null,
    validateResult: Boolean = true,
): Publisher<out MilkyApiResult<R>> = asPublisher {
    executeApiResult(api, baseUrl, accessToken, validateResult)
}

/**
 * 使用 [HttpClient] 执行 [api]，并直接获取 Milky API 成功响应中的 `data` 内容。
 *
 * 此函数会固定启用 HTTP Status 校验与 Milky API 返回体校验，相当于执行 [executeApiResult] 后返回
 * [MilkyApiResult.data]。
 * 此函数是 [executeApiContent] 的 JVM 阻塞桥接。
 *
 * @param R API 成功数据类型。
 * @param api 要被执行的有类型 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 *
 * @return Milky API 成功响应中的 `data` 内容。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 * @throws MilkyApiResultFailedStatusException 如果 [MilkyApiResult.retcode] 不为 [MilkyApiResult.SUCCESS_RETCODE]。
 *
 * @see executeApiContent
 */
@Api4J
@Blocking
public fun <R : Any> HttpClient.executeApiContentBlocking(
    api: TypedMilkyApi<R>,
    baseUrl: String,
    accessToken: String? = null,
): R = runInNoScopeBlocking {
    executeApiContent(api, baseUrl, accessToken)
}

/**
 * 使用 [HttpClient] 执行 [api]，并直接获取 Milky API 成功响应中的 `data` 内容。
 *
 * 此函数会固定启用 HTTP Status 校验与 Milky API 返回体校验，相当于执行 [executeApiResult] 后返回
 * [MilkyApiResult.data]。
 * 此函数是 [executeApiContent] 的 JVM 阻塞桥接。
 *
 * @param R API 成功数据类型。
 * @param api 要被执行的有类型 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 *
 * @return Milky API 成功响应中的 `data` 内容。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 * @throws MilkyApiResultFailedStatusException 如果 [MilkyApiResult.retcode] 不为 [MilkyApiResult.SUCCESS_RETCODE]。
 *
 * @see executeApiContent
 */
@Api4J
@Blocking
public fun <R : Any> HttpClient.executeApiContentBlocking(
    api: TypedMilkyApi<R>,
    baseUrl: Url,
    accessToken: String? = null,
): R = runInNoScopeBlocking {
    executeApiContent(api, baseUrl, accessToken)
}

/**
 * 使用 [HttpClient] 执行 [api]，并直接获取 Milky API 成功响应中的 `data` 内容。
 *
 * 此函数会固定启用 HTTP Status 校验与 Milky API 返回体校验，相当于执行 [executeApiResult] 后返回
 * [MilkyApiResult.data]。
 * 此函数是 [executeApiContent] 的 JVM 异步桥接，返回 [CompletableFuture]。
 *
 * @param R API 成功数据类型。
 * @param api 要被执行的有类型 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 *
 * @return Milky API 成功响应中的 `data` 内容。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 * @throws MilkyApiResultFailedStatusException 如果 [MilkyApiResult.retcode] 不为 [MilkyApiResult.SUCCESS_RETCODE]。
 *
 * @see executeApiContent
 */
@OptIn(InternalSimbotAPI::class)
@Api4J
@NonBlocking
public fun <R : Any> HttpClient.executeApiContentAsync(
    api: TypedMilkyApi<R>,
    baseUrl: String,
    accessToken: String? = null,
): CompletableFuture<out R> = runInAsync {
    executeApiContent(api, baseUrl, accessToken)
}

/**
 * 使用 [HttpClient] 执行 [api]，并直接获取 Milky API 成功响应中的 `data` 内容。
 *
 * 此函数会固定启用 HTTP Status 校验与 Milky API 返回体校验，相当于执行 [executeApiResult] 后返回
 * [MilkyApiResult.data]。
 * 此函数是 [executeApiContent] 的 JVM 异步桥接，返回 [CompletableFuture]。
 *
 * @param R API 成功数据类型。
 * @param api 要被执行的有类型 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 *
 * @return Milky API 成功响应中的 `data` 内容。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 * @throws MilkyApiResultFailedStatusException 如果 [MilkyApiResult.retcode] 不为 [MilkyApiResult.SUCCESS_RETCODE]。
 *
 * @see executeApiContent
 */
@OptIn(InternalSimbotAPI::class)
@Api4J
@NonBlocking
public fun <R : Any> HttpClient.executeApiContentAsync(
    api: TypedMilkyApi<R>,
    baseUrl: Url,
    accessToken: String? = null,
): CompletableFuture<out R> = runInAsync {
    executeApiContent(api, baseUrl, accessToken)
}

/**
 * 使用 [HttpClient] 执行 [api]，并直接获取 Milky API 成功响应中的 `data` 内容。
 *
 * 此函数会固定启用 HTTP Status 校验与 Milky API 返回体校验，相当于执行 [executeApiResult] 后返回
 * [MilkyApiResult.data]。
 * 此函数是 [executeApiContent] 的 JVM Reactive 桥接，返回 Reactive Streams [Publisher]。
 * 可以在引入 [reactor-core](https://github.com/reactor/reactor-core) 后转为 `Mono`：
 *
 * ```kotlin
 * val mono = reactor.core.publisher.Mono.from(client.executeApiContentReactive(api, baseUrl))
 * ```
 *
 * 如果需要与 Kotlin 协程的 Reactor API 互操作，可另行引入
 * [kotlinx-coroutines-reactor](https://github.com/Kotlin/kotlinx.coroutines/tree/master/reactive/kotlinx-coroutines-reactor)。
 *
 * @param R API 成功数据类型。
 * @param api 要被执行的有类型 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 *
 * @return Milky API 成功响应中的 `data` 内容。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 * @throws MilkyApiResultFailedStatusException 如果 [MilkyApiResult.retcode] 不为 [MilkyApiResult.SUCCESS_RETCODE]。
 *
 * @see executeApiContent
 */
@OptIn(InternalSimbotAPI::class)
@Api4J
public fun <R : Any> HttpClient.executeApiContentReactive(
    api: TypedMilkyApi<R>,
    baseUrl: String,
    accessToken: String? = null,
): Publisher<out R> = asPublisher {
    executeApiContent(api, baseUrl, accessToken)
}

/**
 * 使用 [HttpClient] 执行 [api]，并直接获取 Milky API 成功响应中的 `data` 内容。
 *
 * 此函数会固定启用 HTTP Status 校验与 Milky API 返回体校验，相当于执行 [executeApiResult] 后返回
 * [MilkyApiResult.data]。
 * 此函数是 [executeApiContent] 的 JVM Reactive 桥接，返回 Reactive Streams [Publisher]。
 * 可参考同名 `String` 重载中转为 `Mono` 的示例。
 *
 * @param R API 成功数据类型。
 * @param api 要被执行的有类型 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 *
 * @return Milky API 成功响应中的 `data` 内容。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 HTTP Status 不在 `200..<300`。
 * @throws MilkyApiResultFailedStatusException 如果 [MilkyApiResult.retcode] 不为 [MilkyApiResult.SUCCESS_RETCODE]。
 *
 * @see executeApiContent
 */
@OptIn(InternalSimbotAPI::class)
@Api4J
public fun <R : Any> HttpClient.executeApiContentReactive(
    api: TypedMilkyApi<R>,
    baseUrl: Url,
    accessToken: String? = null,
): Publisher<out R> = asPublisher {
    executeApiContent(api, baseUrl, accessToken)
}
