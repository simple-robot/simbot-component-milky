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
 * [image 图片消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-image).
 */
@Serializable
@SerialName(MilkyRawIncomingImageMessageSegment.TYPE)
@MilkyRawIncomingMessageSegmentTypeMarker(MilkyRawIncomingImageMessageSegment.TYPE)
public data class MilkyRawIncomingImageMessageSegment
@MilkyEventModelConstructor
internal constructor(
    /**
     * 消息段数据。
     */
    public val data: Data,
) : MilkyRawIncomingMessageSegment {
    /**
     * 图片消息段数据。
     */
    @Serializable
    public data class Data
    @MilkyEventModelConstructor
    internal constructor(
        /**
         * 资源 ID
         */
        @SerialName("resource_id")
        public val resourceId: String,
        /**
         * 临时 URL
         */
        @SerialName("temp_url")
        public val tempUrl: String,
        /**
         * 图片宽度
         */
        public val width: Int,
        /**
         * 图片高度
         */
        public val height: Int,
        /**
         * 图片预览文本
         */
        public val summary: String,
        /**
         * 图片类型
         */
        @SerialName("sub_type")
        public val subType: MilkyRawIncomingImageSubType,
    )

    public companion object {
        public const val TYPE: String = "image"
    }
}
