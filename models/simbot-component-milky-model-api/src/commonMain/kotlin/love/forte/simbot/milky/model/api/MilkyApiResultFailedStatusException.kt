package love.forte.simbot.milky.model.api

/**
 * 当 [MilkyApiResult] 的状态码表示失败时抛出的异常。
 * @author Forte Scarlet
 */
public class MilkyApiResultFailedStatusException : RuntimeException {
    /**
     * @see MilkyApiResult.status
     */
    public val status: String

    /**
     * @see MilkyApiResult.retcode
     */
    public val retcode: Int

    /**
     * @see MilkyApiResult.message
     */
    public val resultMessage: String?

    public constructor(status: String, retcode: Int, resultMessage: String?) : super("$status $retcode: $resultMessage") {
        this.status = status
        this.retcode = retcode
        this.resultMessage = resultMessage
    }

    public constructor(status: String, retcode: Int, resultMessage: String?, message: String?) : super(message) {
        this.status = status
        this.retcode = retcode
        this.resultMessage = resultMessage
    }
}

/**
 * 将 [MilkyApiResult] 转换为 [MilkyApiResultFailedStatusException]。
 * 注：不会校验是否真的是失败状态。
 */
public fun MilkyApiResult<*>.toFailedStatusException(): MilkyApiResultFailedStatusException {
    return MilkyApiResultFailedStatusException(
        status = status,
        retcode = retcode,
        resultMessage = message,
        message = "Milky API result failed($status:$retcode): $message"
    )
}
