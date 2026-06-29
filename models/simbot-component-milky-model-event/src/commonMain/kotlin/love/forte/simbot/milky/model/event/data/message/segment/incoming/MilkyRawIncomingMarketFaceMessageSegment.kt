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
 * [market_face 市场表情消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-market_face).
 */
@Serializable
@SerialName(MilkyRawIncomingMarketFaceMessageSegment.TYPE)
@MilkyRawIncomingMessageSegmentTypeMarker(MilkyRawIncomingMarketFaceMessageSegment.TYPE)
public data class MilkyRawIncomingMarketFaceMessageSegment
@MilkyEventModelConstructor
internal constructor(
    /**
     * 消息段数据。
     */
    public val data: Data,
) : MilkyRawIncomingMessageSegment {
    /**
     * 市场表情消息段数据。
     */
    @Serializable
    public data class Data
    @MilkyEventModelConstructor
    internal constructor(
        /**
         * 市场表情包 ID
         */
        @SerialName("emoji_package_id")
        public val emojiPackageId: Int,
        /**
         * 市场表情 ID
         */
        @SerialName("emoji_id")
        public val emojiId: String,
        /**
         * 市场表情 Key
         */
        public val key: String,
        /**
         * 市场表情预览文本
         */
        public val summary: String,
        /**
         * 市场表情 URL
         */
        public val url: String,
    )

    public companion object {
        public const val TYPE: String = "market_face"
    }
}
