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

package love.forte.simbot.milky.model.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Milky 基础事件结构体。
 *
 * [MilkyRawEvent] 类型及其所有子类型均为底层用于数据交互的原始数据类，
 * 除了序列化以外不应直接通过构造函数构造它们。
 *
 * 参考：[Milky 文档: 结构体 > 事件](https://milky.ntqqrev.org/struct/Event)
 * @author Forte Scarlet
 */
@Serializable
public abstract class MilkyRawEvent<D : Any> {
    /**
     * 类型区分字段
     */
    @SerialName("event_type")
    public abstract val eventType: String

    /**
     * 事件 Unix 时间戳（秒）
     */
    public abstract val time: Long

    /**
     * 机器人 QQ 号
     */
    public abstract val selfId: Long

    /**
     * 事件内容体。
     * [data] 在不同的 [eventType] 下的具体类型不同。
     */
    public abstract val data: D

    override fun toString(): String {
        return "MilkyRawEvent(eventType='$eventType', time=$time, selfId=$selfId, data=$data)"
    }
}
