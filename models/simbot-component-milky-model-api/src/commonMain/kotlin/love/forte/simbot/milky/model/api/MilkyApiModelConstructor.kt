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

package love.forte.simbot.milky.model.api


/**
 * 标记一个用来构造 Milky API model 实体模型的构造函数。
 *
 * API 模型类型/构造通常是用于内部流转或 Low-Level API 的类型，
 * 它们通常是 data class 之类的纯数据载体类型，没有额外的能力或业务。
 *
 * 这些类型的构造方式可能会随着版本的演进而发生变化，因此对他们的构造函数来讲可能无法保证兼容性,
 * 例如字段数量、顺序、类型发生了变化等。
 * 因此不应在外部其他地方由用户或任何第三方来手动构造它们，以免产生任何兼容性风险。
 */
@RequiresOptIn(
    message = "一个用来构造 Milky API model 模型的构造函数，" +
        "不应在外部其他地方由用户或任何第三方来手动构造它们，以免产生任何兼容性风险。"
)
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CONSTRUCTOR)
@MustBeDocumented
public annotation class MilkyApiModelConstructor {
    // TODO
}
