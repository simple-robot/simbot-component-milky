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
 * [xml XML 消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-xml).
 */
@Serializable
@SerialName(MilkyRawIncomingXmlMessageSegment.TYPE)
@MilkyRawIncomingMessageSegmentTypeMarker(MilkyRawIncomingXmlMessageSegment.TYPE)
public data class MilkyRawIncomingXmlMessageSegment
@MilkyEventModelConstructor
internal constructor(
    /**
     * 消息段数据。
     */
    public val data: Data,
) : MilkyRawIncomingMessageSegment {
    /**
     * XML 消息段数据。
     */
    @Serializable
    public data class Data
    @MilkyEventModelConstructor
    internal constructor(
        /**
         * 服务 ID
         */
        @SerialName("service_id")
        public val serviceId: Int,
        /**
         * XML 数据
         */
        @SerialName("xml_payload")
        public val xmlPayload: String,
    )

    public companion object {
        public const val TYPE: String = "xml"
    }
}
