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

package love.forte.simbot.milky.model.event.data.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.event.data.MilkyRawEventData
import love.forte.simbot.milky.model.event.data.message.segment.incoming.MilkyRawIncomingMessageSegment

/**
 *
 * @author Forte Scarlet
 */
@Serializable
@SerialName(MilkyRawIncomingMessageEventData.SERIAL_NAME)
public abstract class MilkyRawIncomingMessageEventData : MilkyRawEventData() {
    /**
     * 消息场景。
     */
    public abstract val messageScene: MilkyRawMessageScene

    /**
     * 好友 QQ 号或群号。
     */
    public abstract val peerId: Long

    /**
     * 消息序列号。
     */
    public abstract val messageSeq: Long

    /**
     * 发送者 QQ 号。
     */
    public abstract val senderId: Long

    /**
     * 消息 Unix 时间戳（秒）。
     */
    public abstract val time: Long

    /**
     * 消息片段的原始数据列表。
     *
     * TODO `IncomingSegment` 尚未定义，当前先以 `Unit` 占位。
     */
    public abstract val segments: List<MilkyRawIncomingMessageSegment>

    public companion object {
        public const val EVENT_TYPE: String = "incoming_message"
        public const val SERIAL_NAME: String = "$SERIAL_NAME_PREFIX$EVENT_TYPE"
    }

}

/**
 * 用于注解处理器对 [MilkyRawIncomingMessageEventData] 序列化器进行解析与处理的标记注解。
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
public annotation class MilkyRawIncomingMessageEventDataMarker(val messageScene: String)
