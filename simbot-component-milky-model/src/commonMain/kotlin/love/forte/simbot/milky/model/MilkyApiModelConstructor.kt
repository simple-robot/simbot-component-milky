package love.forte.simbot.milky.model

/**
 * 标记一个用来构造 Milky API 模型的构造函数。
 * API 模型类型/构造通常是用于内部流转或 Low-Level API 的类型，
 * 它们通常是 data class 之类的纯数据载体类型，没有额外的能力或业务。
 * 这些类型的构造方式可能会随着版本的演进而发生变化，因此对他们的构造函数来讲可能无法保证兼容性,
 * 例如字段数量、顺序、类型发生了变化等。
 * 因此不应在外部其他地方由用户或任何第三方来手动构造它们，以免产生任何兼容性风险。
 */
@RequiresOptIn(
    message = "一个用来构造 Milky API 模型的构造函数，" +
        "不应在外部其他地方由用户或任何第三方来手动构造它们，以免产生任何兼容性风险。"
)
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CONSTRUCTOR)
public annotation class MilkyApiModelConstructor
