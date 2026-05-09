package love.forte.simbot.milky.api

/**
 * 标记一个用于内部的 API 序列化器，它的规则可能会随着版本演进而产生变化，
 * 不保证其对外使用的稳定与兼容性。
 */
@RequiresOptIn(
    message = "一个用于内部 API 相关内容使用的 JSON 序列化器。" +
        "它的规则可能会随着版本演进而产生变化，不保证其对外使用的稳定与兼容性。"
)
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
@MustBeDocumented
public annotation class InternalMilkyApiSerializerJson
