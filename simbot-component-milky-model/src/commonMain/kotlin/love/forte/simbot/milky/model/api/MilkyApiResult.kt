package love.forte.simbot.milky.model.api

import kotlinx.serialization.Serializable

/**
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
