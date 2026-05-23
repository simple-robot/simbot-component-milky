package love.forte.simbot.milky.model.api

import kotlinx.serialization.Serializable

/**
 * Milky API 的统一返回结构体。
 *
 * **下文片段摘抄自官方文档(v1.2.2)**。
 *
 * 收到 API 请求并处理后，协议端会返回一个 HTTP 响应，根据具体错误类型不同，HTTP 状态码不同：
 *
 * - `401`：鉴权凭据未提供或不匹配。
 * - `404`：请求的 API 不存在。
 * - `415`：POST 请求的 Content-Type 不支持。
 *
 * 剩下的所有情况，无论操作实际成功与否，状态码都是 200，同时返回 JSON 格式的响应，示例如下：
 *
剩下的所有情况，无论操作实际成功与否，状态码都是 200，同时返回 JSON 格式的响应，示例如下：
 *
 * ```JSON
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
 * ```JSON
 * // 失败响应示例 0
 * {
 *   "status": "failed",
 *   "retcode": -403, // 协议端未处于登录状态时，retcode 为 -403
 *   "message": "未处于登录状态"
 * }
 * ```
 *
 * ```JSON
 * // 失败响应示例 1
 * {
 *   "status": "failed",
 *   "retcode": -400, // 参数解析失败时，retcode 为 -400
 *   "message": "user_id (-1) 不是一个合法的 QQ 号"
 * }
 * ```
 *
 * ```JSON
 * // 失败响应示例 2
 * {
 *   "status": "failed",
 *   "retcode": -404, // 其余错误情况的 retcode 由协议端自行决定
 *   "message": "user_id 对应的好友不存在"
 * }
 * ```
 *
 * 同样，即使响应的 API 无输出参数，也必须返回一个空的 JSON 对象 {}，例如：
 * ```JSON
 * {
 *   "status": "ok",
 *   "retcode": 0,
 *   "data": {}
 * }
 * ```
 *
 * @author Forte Scarlet
 */
@Serializable
public class MilkyApiResult<out T : Any> private constructor(
    /**
     * 响应状态的描述信息
     */
    public val status: String = "",
    /**
     * 响应状态码
     */
    public val retcode: Int,
    /**
     * 失败响应时的错误提示信息，成功时可能不存在。
     */
    public val message: String? = null,
    /**
     * 响应数据，失败时可能不存在。
     */
    public val data: T? = null
) {
    public companion object {
        /**
         * 成功时的 [retcode] 为 `0`
         */
        public const val SUCCESS_RETCODE: Int = 0

    }
}
