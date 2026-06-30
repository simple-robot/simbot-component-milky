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

import io.ktor.http.*

/**
 * Milky API 在执行期间或执行结果为错误时。
 *
 * @author Forte Scarlet
 */
@Suppress("RedundantVisibilityModifier")
public sealed class MilkyApiExecutionException : RuntimeException {

    protected constructor() : super()
    protected constructor(cause: Throwable?) : super(cause)
    protected constructor(message: String?) : super(message)
    protected constructor(message: String?, cause: Throwable?) : super(message, cause)
}

/**
 * Milky API 的执行结果为错误时（Http Status 不在 `200..<300`）。
 *
 * 参考 [官方文档#API 调用](https://milky.ntqqrev.org/guide/communication#api-调用) 中的相关描述：
 * 收到 API 请求并处理后，协议端会返回一个 HTTP 响应，根据具体错误类型不同，HTTP 状态码不同：
 * - `401`：鉴权凭据未提供或不匹配。
 * - `404`：请求的 API 不存在。
 * - `415`：POST 请求的 Content-Type 不支持。
 *
 */
@Suppress("RedundantVisibilityModifier")
public sealed class MilkyApiHttpStatusException : MilkyApiExecutionException {
    /**
     * 错误码。
     */
    public abstract val httpStatusCode: HttpStatusCode

    protected constructor() : super()
    protected constructor(cause: Throwable?) : super(cause)
    protected constructor(message: String?) : super(message)
    protected constructor(message: String?, cause: Throwable?) : super(message, cause)
}

/**
 * Milky API 响应 `401` 的鉴权失败异常
 */
public class MilkyApiAuthenticationFailureException : MilkyApiHttpStatusException {
    override val httpStatusCode: HttpStatusCode get() = HttpStatusCode.Unauthorized

    public constructor() : super()
    public constructor(message: String?) : super(message)
}

/**
 * Milky API 响应 `404` 的 API 不存在异常
 */
public class MilkyApiNotFoundException : MilkyApiHttpStatusException {
    override val httpStatusCode: HttpStatusCode get() = HttpStatusCode.NotFound

    public constructor() : super()
    public constructor(message: String?) : super(message)
}

/**
 * Milky API 响应 `415` 的 Content-Type 不支持异常
 */
public class MilkyApiUnsupportedMediaTypeException : MilkyApiHttpStatusException {
    override val httpStatusCode: HttpStatusCode get() = HttpStatusCode.UnsupportedMediaType

    public constructor() : super()
    public constructor(message: String?) : super(message)
}

/**
 * Milky API 响应出现未知（预期外）错误码的异常
 */
public class MilkyApiUnknownException : MilkyApiHttpStatusException {
    override val httpStatusCode: HttpStatusCode

    public constructor(httpStatusCode: HttpStatusCode) : super() {
        this.httpStatusCode = httpStatusCode
    }

    public constructor(httpStatusCode: HttpStatusCode, cause: Throwable?) : super(cause) {
        this.httpStatusCode = httpStatusCode
    }

    public constructor(httpStatusCode: HttpStatusCode, message: String?) : super(message) {
        this.httpStatusCode = httpStatusCode
    }

    public constructor(httpStatusCode: HttpStatusCode, message: String?, cause: Throwable?) : super(message, cause) {
        this.httpStatusCode = httpStatusCode
    }
}

