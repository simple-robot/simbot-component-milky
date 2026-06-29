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

package love.forte.simbot.milky.model.event.data.message.segment.incoming

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.event.MilkyEventModelConstructor

/**
 * [file 文件消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-file).
 */
@Serializable
@SerialName(MilkyRawIncomingFileMessageSegment.TYPE)
@MilkyRawIncomingMessageSegmentTypeMarker(MilkyRawIncomingFileMessageSegment.TYPE)
public data class MilkyRawIncomingFileMessageSegment
@MilkyEventModelConstructor
internal constructor(
    /**
     * 消息段数据。
     */
    public val data: Data,
) : MilkyRawIncomingMessageSegment {
    /**
     * 文件消息段数据。
     */
    @Serializable
    public data class Data
    @MilkyEventModelConstructor
    internal constructor(
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
         * 文件的 TriSHA1 哈希值，仅在私聊文件中存在
         */
        @SerialName("file_hash")
        public val fileHash: String? = null,
    )

    public companion object {
        public const val TYPE: String = "file"
    }
}
