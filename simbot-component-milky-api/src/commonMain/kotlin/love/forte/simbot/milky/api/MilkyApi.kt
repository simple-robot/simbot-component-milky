package love.forte.simbot.milky.api

import io.ktor.http.*
import kotlinx.serialization.json.Json
import love.forte.simbot.milky.api.MilkyApi.Companion.defaultJson

/**
 * 一个 Milky 的 API。API 总是基于 HTTP 协议与 JSON 格式交互的。
 *
 * [MilkyApi] 主要作为 Milky 的 API 接口定义与数据载体，本身 **不包含** 发起请求等网络/IO的能力。
 *
 * ## API 调用
 *
 * > NOTE: 以下内容摘抄引用自官方文档。
 *
 * 接受路径为 `/api/:api` 的 API 请求。请求使用 POST 方法，在请求体中通过 JSON 传递参数。
 * 为保证安全性，可以在配置文件中设置 `access_token`，协议端需要在请求头中检查 `Authorization` 字段，
 * 格式为 `Bearer {access_token}`。
 *
 * 示例如下：
 *
 * ```
 * POST /api/send_private_message
 * Content-Type: application/json
 * Authorization: Bearer 123456
 *
 * {
 *   "user_id": 123456789,
 *   "message": [
 *     {
 *       "type": "text",
 *       "data": {
 *         "text": "Hello, world!"
 *       }
 *     }
 *   ]
 * }
 * ```
 *
 * 注意，即使请求的 API 无输入参数，也必须传入一个空的 JSON 对象 {}，例如：
 *
 * ```
 * POST /api/get_login_info
 * Content-Type: application/json
 * Authorization: Bearer 123456
 *
 * {}
 * ```
 *
 * 收到 API 请求并处理后，协议端会返回一个 HTTP 响应，根据具体错误类型不同，HTTP 状态码不同：
 *
 * - `401`：鉴权凭据未提供或不匹配。
 * - `404`：请求的 API 不存在。
 * - `415`：POST 请求的 Content-Type 不支持。
 *
 * 剩下的所有情况，无论操作实际成功与否，状态码都是 200，同时返回 JSON 格式的响应，示例如下：
 *
 * ```
 * // 成功响应示例
 * {
 *   "status": "ok",
 *   "retcode": 0, // 成功时的 retcode 为 0
 *   "data": {
 *     "message_seq": 23333,
 *     "time": 1234567890
 *   }
 * }
 * ```
 *
 * ```
 * // 失败响应示例 0
 * {
 *   "status": "failed",
 *   "retcode": -403, // 协议端未处于登录状态时，retcode 为 -403
 *   "message": "未处于登录状态"
 * }
 * ```
 *
 * ```
 * // 失败响应示例 1
 * {
 *   "status": "failed",
 *   "retcode": -400, // 参数解析失败时，retcode 为 -400
 *   "message": "user_id (-1) 不是一个合法的 QQ 号"
 * }
 * ```
 *
 * ```
 * // 失败响应示例 2
 * {
 *   "status": "failed",
 *   "retcode": -404, // 其余错误情况的 retcode 由协议端自行决定
 *   "message": "user_id 对应的好友不存在"
 * }
 * ```
 *
 * 同样，即使响应的 API 无输出参数，也必须返回一个空的 JSON 对象 {}，例如：
 * ```
 * {
 *   "status": "ok",
 *   "retcode": 0,
 *   "data": {}
 * }
 * ```
 *
 * 参考：
 * - [Milky 文档 - 通信](https://milky.ntqqrev.org/guide/communication)
 *
 * ## 请求体
 *
 * [MilkyApi] 类型本身不约束任何入参/出参的类型规约，因为站在 API 交互的角度上，它们都是 JSON 字符串进行交互的。
 * 但进行此类型的实现时，推荐（且内部所有实现均遵循推荐）使用 [TypedMilkyApi]：对出参进行类型范型约束的子类型。
 *
 * @author Forte Scarlet
 */
public interface MilkyApi {
    /**
     * 请求方式。Milky 的主要请求方式均为 [HttpMethod.Post]。因此默认值为 [HttpMethod.Post]。
     */
    public val method: HttpMethod
        get() = HttpMethod.Post

    /**
     * 请求目标的相对路径段。
     *
     * 路径中的每一段都应当已经完成 URL path encode。
     *
     * ## API 目标资源定义
     *
     * Milky 的请求路径通常为 `/api/:api`，例如 `/api/send_private_message`，
     * 其可视为：
     * 1. 一个绝对资源路径 `/api/send_private_message`。
     * 2. 一个在根路径 `/` 中的相对资源 `api/send_private_message`。
     *
     * 而在 [MilkyApi] 中，我们只通过 [apiName] 定义其中的 `:api` 部分，而前缀路径等内容作为 base url 的一部分。
     */
    public val apiName: String

    /**
     * 获取请求体内容。返回值为 JSON 字符串。
     * 默认情况下，通常内部会使用 [defaultJson] 进行序列化与反序列化。
     *
     * @return 请求体内容的 JSON 字符串，如果请求体为空则返回 null。
     */
    public fun bodyContent(): String?

    public companion object {
        /**
         * 一个默认用于内部 API 相关内容使用的 JSON 序列化器。
         */
        @InternalMilkyApiSerializerJson
        public val defaultJson: Json = Json {
            isLenient = true
            ignoreUnknownKeys = true
            encodeDefaults = true
            // TODO modules ?
        }

    }
}
