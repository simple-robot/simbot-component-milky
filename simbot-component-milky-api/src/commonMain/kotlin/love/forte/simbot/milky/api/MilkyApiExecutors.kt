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
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import love.forte.simbot.milky.model.api.MilkyApiResult
import love.forte.simbot.milky.model.api.MilkyApiResultFailedStatusException
import love.forte.simbot.milky.model.api.validate
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * 使用 [HttpClient] 执行 [api] 并得到响应结果 [HttpResponse]。
 *
 * ## [Milky 通信](https://milky.ntqqrev.org/guide/communication)
 *
 * Milky 的协议端实现需要开启一个 HTTP 服务器，在 `/api` 端点提供 API 调用服务，
 * 应用端向协议端发起请求，协议端继而完成响应操作。
 *
 * ### API 调用
 *
 * 有关 Milky API 调用的详细说明参考 [MilkyApi] 的说明或
 * [官方文档](https://milky.ntqqrev.org/guide/communication#api-调用)。
 *
 * @receiver 执行 api 的 [HttpClient]。
 * @param api 要被执行的 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 * @param validateStatus 是否校验 HTTP Status。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 [validateStatus] 为 `true` 且 HTTP Status 不在 `200..<300`。
 *
 * @see MilkyApi
 */
public suspend fun HttpClient.executeApi(
    api: MilkyApi,
    baseUrl: String,
    accessToken: String? = null,
    validateStatus: Boolean = true,
): HttpResponse {
    val apiUrl = URLBuilder(baseUrl)
        .appendEncodedPathSegments(api.apiName)
        .build()

    return executeApi0(api, apiUrl, accessToken, validateStatus)
}

/**
 * 使用 [HttpClient] 执行 [api] 并得到响应结果 [HttpResponse]。
 *
 * 详细说明参考 [executeApi]。
 *
 * @receiver 执行 api 的 [HttpClient]。
 * @param api 要被执行的 Milky API。
 * @param baseUrl API 的基础访问地址。注意包括访问端点前缀，例如 `http://localhost:8080/api`。
 * @param accessToken Milky 协议端配置的访问令牌。如果不为 `null`，会作为 `Bearer` token 写入 `Authorization` 请求头。
 * @param validateStatus 是否校验 HTTP Status。
 *
 * @throws kotlinx.serialization.SerializationException 对出/入参进行序列化时可能出现的错误。
 * 可能源自 execute 内，也可能源自 [MilkyApi.bodyContent]。
 * @throws MilkyApiExecutionException 如果 [validateStatus] 为 `true` 且 HTTP Status 不在 `200..<300`。
 *
 * @see MilkyApi
 */
public suspend fun HttpClient.executeApi(
    api: MilkyApi,
    baseUrl: Url,
    accessToken: String? = null,
    validateStatus: Boolean = true,
): HttpResponse {
    val apiUrl = URLBuilder(baseUrl)
        .appendEncodedPathSegments(api.apiName)
        .build()

    return executeApi0(api, apiUrl, accessToken, validateStatus)
}

/**
 * 使用 [HttpClient] 执行 [api] 并将响应体读取为字符串。
 *
 * 此函数会固定启用 HTTP Status 校验；如果只需要原始 [HttpResponse] 或需要关闭 HTTP Status 校验，
 * 使用 [executeApi]。
 *
 * @receiver 执行 api 的 [HttpClient]。
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
 * @see executeApi
 */
public suspend fun HttpClient.executeApiText(
    api: MilkyApi,
    baseUrl: String,
    accessToken: String? = null,
): String {
    val response = executeApi(api, baseUrl, accessToken, validateStatus = true)
    return response.bodyAsText()
}

/**
 * 使用 [HttpClient] 执行 [api] 并将响应体读取为字符串。
 *
 * 此函数会固定启用 HTTP Status 校验；如果只需要原始 [HttpResponse] 或需要关闭 HTTP Status 校验，
 * 使用 [executeApi]。
 *
 * @receiver 执行 api 的 [HttpClient]。
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
 * @see executeApi
 */
public suspend fun HttpClient.executeApiText(
    api: MilkyApi,
    baseUrl: Url,
    accessToken: String? = null,
): String {
    val response = executeApi(api, baseUrl, accessToken, validateStatus = true)
    return response.bodyAsText()
}

/**
 * 使用 [HttpClient] 执行 [api]，并将响应体解析为 Milky API 的统一返回结构 [MilkyApiResult]。
 *
 * 此函数会固定启用 HTTP Status 校验。若 [validateResult] 为 `true`，还会校验 Milky 返回结构中的
 * [MilkyApiResult.retcode] 是否为 [MilkyApiResult.SUCCESS_RETCODE]。
 *
 * @receiver 执行 api 的 [HttpClient]。
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
 * @see TypedMilkyApi.decodeResult
 * @see MilkyApiResult.validate
 */
public suspend fun <R : Any> HttpClient.executeApiResult(
    api: TypedMilkyApi<R>,
    baseUrl: String,
    accessToken: String? = null,
    validateResult: Boolean = true,
): MilkyApiResult<R> {
    val content = executeApiText(api, baseUrl, accessToken)
    val result = api.decodeResult(content)
    if (validateResult) {
        result.validate()
    }
    return result
}


/**
 * 使用 [HttpClient] 执行 [api]，并将响应体解析为 Milky API 的统一返回结构 [MilkyApiResult]。
 *
 * 此函数会固定启用 HTTP Status 校验。若 [validateResult] 为 `true`，还会校验 Milky 返回结构中的
 * [MilkyApiResult.retcode] 是否为 [MilkyApiResult.SUCCESS_RETCODE]。
 *
 * @receiver 执行 api 的 [HttpClient]。
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
 * @see TypedMilkyApi.decodeResult
 * @see MilkyApiResult.validate
 */
public suspend fun <R : Any> HttpClient.executeApiResult(
    api: TypedMilkyApi<R>,
    baseUrl: Url,
    accessToken: String? = null,
    validateResult: Boolean = true,
): MilkyApiResult<R> {
    val content = executeApiText(api, baseUrl, accessToken)
    val result = api.decodeResult(content)
    if (validateResult) {
        result.validate()
    }
    return result
}

/**
 * 使用 [HttpClient] 执行 [api]，并直接获取 Milky API 成功响应中的 `data` 内容。
 *
 * 此函数会固定启用 HTTP Status 校验与 Milky API 返回体校验，相当于执行 [executeApiResult] 后返回
 * [MilkyApiResult.data]。
 *
 * @receiver 执行 api 的 [HttpClient]。
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
 * @see executeApiResult
 */
public suspend fun <R : Any> HttpClient.executeApiContent(
    api: TypedMilkyApi<R>,
    baseUrl: String,
    accessToken: String? = null,
): R {
    val result = executeApiResult(api, baseUrl, accessToken, validateResult = true)
    return result.data!!
}


/**
 * 使用 [HttpClient] 执行 [api]，并直接获取 Milky API 成功响应中的 `data` 内容。
 *
 * 此函数会固定启用 HTTP Status 校验与 Milky API 返回体校验，相当于执行 [executeApiResult] 后返回
 * [MilkyApiResult.data]。
 *
 * @receiver 执行 api 的 [HttpClient]。
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
 * @see executeApiResult
 */
public suspend fun <R : Any> HttpClient.executeApiContent(
    api: TypedMilkyApi<R>,
    baseUrl: Url,
    accessToken: String? = null,
): R {
    val result = executeApiResult(api, baseUrl, accessToken, validateResult = true)
    return result.data!!
}


private suspend fun HttpClient.executeApi0(
    api: MilkyApi,
    url: Url,
    accessToken: String?,
    validateStatus: Boolean
): HttpResponse {
    val response = doExecuteApi(api, url, accessToken)

    if (validateStatus) {
        response.validate()
    }

    return response
}

private const val EMPTY_CONTENT = "{}"

private suspend fun HttpClient.doExecuteApi(
    api: MilkyApi,
    url: Url,
    accessToken: String? = null
): HttpResponse {
    return request(url) {
        method = api.method
        contentType(ContentType.Application.Json)
        if (accessToken != null) {
            header(HttpHeaders.Authorization, "Bearer $accessToken")
        }

        val body = api.bodyContent() ?: EMPTY_CONTENT
        setBody(body)
    }
}

private suspend fun HttpResponse.validate() {
    val statusValue = this.status

    suspend fun readContent(): String {
        return bodyAsText()
    }

    fun resolveDescription(base: HttpStatusCode): String {
        if (statusValue === base || statusValue.description == base.description) {
            return base.description
        }

        return statusValue.description
    }

    suspend fun String.appendContentIfExists(): String {
        val content = readContent()
        if (content.isNotBlank()) {
            return "$this: $content"
        }
        return this
    }

    suspend fun resolveErrorMessage(base: HttpStatusCode): String {
        return resolveDescription(base).appendContentIfExists()
    }

    when (statusValue.value) {
        401 -> throw MilkyApiAuthenticationFailureException(resolveErrorMessage(HttpStatusCode.Unauthorized))
        404 -> throw MilkyApiNotFoundException(resolveErrorMessage(HttpStatusCode.NotFound))
        415 -> throw MilkyApiUnsupportedMediaTypeException(resolveErrorMessage(HttpStatusCode.UnsupportedMediaType))
        else -> {
            if (!statusValue.isSuccess()) {
                throw MilkyApiUnknownException(statusValue, readContent())
            }
        }
    }
}
