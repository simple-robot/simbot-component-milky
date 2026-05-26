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
 *     You should have received a copy of the Lesser General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package love.forte.simbot.milky.model.event.data.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.event.MilkyEventModelConstructor

/**
 * 临时消息的本地最小原始模型。
 *
 * 当前本地 `data.md` 未提供场景特有字段，
 * 因此先仅保留 `IncomingMessage` 的公共字段。
 *
 * @author Forte Scarlet
 */
@ConsistentCopyVisibility
@Serializable
@SerialName(MilkyRawIncomingTempMessageEventData.SERIAL_NAME)
@MilkyRawIncomingMessageEventDataMarker(MilkyRawIncomingTempMessageEventData.MESSAGE_SCENE)
public data class MilkyRawIncomingTempMessageEventData
@MilkyEventModelConstructor
internal constructor(
    /**
     * 消息场景。
     */
    @SerialName("message_scene")
    override val messageScene: MilkyRawMessageScene = MilkyRawMessageScene.TEMP,
    /**
     * 会话目标 ID。
     */
    @SerialName("peer_id")
    override val peerId: Long,
    /**
     * 消息序列号。
     */
    @SerialName("message_seq")
    override val messageSeq: Long,
    /**
     * 发送者 QQ 号。
     */
    @SerialName("sender_id")
    override val senderId: Long,
    /**
     * 消息 Unix 时间戳（秒）。
     */
    override val time: Long,
    /**
     * 消息片段的原始数据列表。
     */
    override val segments: List<MilkyRawIncomingMessageSegment>,
    /**
     * 临时会话发送者所在的群信息。
     *
     * TODO `GroupEntity` 尚未定义，当前先以 `Unit?` 占位。
     */
    public val group: Unit? = null,
) : MilkyRawIncomingMessageEventData() {
    public companion object {
        public const val MESSAGE_SCENE: String = "temp"
        public const val SERIAL_NAME: String = "${MilkyRawIncomingMessageEventData.SERIAL_NAME}_$MESSAGE_SCENE"
    }
}
