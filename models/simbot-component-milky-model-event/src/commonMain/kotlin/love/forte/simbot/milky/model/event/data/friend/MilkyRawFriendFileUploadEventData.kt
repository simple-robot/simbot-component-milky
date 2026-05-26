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

package love.forte.simbot.milky.model.event.data.friend

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.event.MilkyEventModelConstructor
import love.forte.simbot.milky.model.event.data.MilkyRawEventData
import love.forte.simbot.milky.model.event.data.MilkyRawEventDataMarker

/**
 * [friend_file_upload 好友文件上传事件](https://milky.ntqqrev.org/struct/Event#type-friend_file_upload).
 *
 * @see love.forte.simbot.milky.model.event.MilkyRawEvent
 */
@ConsistentCopyVisibility
@Serializable
@SerialName(MilkyRawFriendFileUploadEventData.SERIAL_NAME)
@MilkyRawEventDataMarker(eventType = MilkyRawFriendFileUploadEventData.EVENT_TYPE)
public data class MilkyRawFriendFileUploadEventData
@MilkyEventModelConstructor
internal constructor(
    /**
     * 好友 QQ 号
     */
    @SerialName("user_id")
    public val userId: Long,
    /**
     * 文件 ID
     */
    @SerialName("file_id")
    public val fileId: String,
    /**
     * 文件名称
     */
    @SerialName("file_name")
    public val fileName: String,
    /**
     * 文件大小（字节）
     */
    @SerialName("file_size")
    public val fileSize: Long,
    /**
     * 文件的 TriSHA1 哈希值
     */
    @SerialName("file_hash")
    public val fileHash: String,
    /**
     * 是否是自己发送的文件
     */
    @SerialName("is_self")
    public val isSelf: Boolean,
) : MilkyRawEventData() {
    public companion object {
        public const val EVENT_TYPE: String = "friend_file_upload"
        public const val SERIAL_NAME: String = SERIAL_NAME_PREFIX + EVENT_TYPE
    }
}
