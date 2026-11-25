/*
 *     Copyright (c) 2026. ForteScarlet.
 *
 *     Project    https://github.com/simple-robot/simbot-component-milky
 *     Email      ForteScarlet@163.com
 *
 *     This file is part of simbot-component-milky.
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

package love.forte.simbot.milky.model.api.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.api.MilkyApiModelConstructor

/**
 * [IncomingSegment 接收消息段](https://milky.ntqqrev.org/struct/IncomingSegment)。
 *
 * Milky 消息段使用顶层 `type` 与内部 `data` 的结构。
 */
@Serializable
public sealed interface MilkyIncomingSegment

/** [text 文本消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-text)。 */
@Serializable
@SerialName(MilkyIncomingTextSegment.TYPE)
public class MilkyIncomingTextSegment
@MilkyApiModelConstructor private constructor(
    public val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public class Data
    @MilkyApiModelConstructor private constructor(
        public val text: String,
    )

    public companion object {
        public const val TYPE: String = "text"
    }
}

/** [mention 提及消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-mention)。 */
@Serializable
@SerialName(MilkyIncomingMentionSegment.TYPE)
public class MilkyIncomingMentionSegment
@MilkyApiModelConstructor private constructor(
    public val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("user_id")
        public val userId: Long,
        public val name: String,
    )

    public companion object {
        public const val TYPE: String = "mention"
    }
}

/** [mention_all 提及全体消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-mention_all)。 */
@Serializable
@SerialName(MilkyIncomingMentionAllSegment.TYPE)
public class MilkyIncomingMentionAllSegment
@MilkyApiModelConstructor private constructor(
    public val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public class Data
    @MilkyApiModelConstructor private constructor()

    public companion object {
        public const val TYPE: String = "mention_all"
    }
}

/** [face 表情消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-face)。 */
@Serializable
@SerialName(MilkyIncomingFaceSegment.TYPE)
public class MilkyIncomingFaceSegment
@MilkyApiModelConstructor private constructor(
    public val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("face_id")
        public val faceId: String,
        @SerialName("is_large")
        public val isLarge: Boolean,
    )

    public companion object {
        public const val TYPE: String = "face"
    }
}

/** [reply 回复消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-reply)。 */
@Serializable
@SerialName(MilkyIncomingReplySegment.TYPE)
public class MilkyIncomingReplySegment
@MilkyApiModelConstructor private constructor(
    public val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("message_seq")
        public val messageSeq: Long,
        @SerialName("sender_id")
        public val senderId: Long,
        @SerialName("sender_name")
        public val senderName: String? = null,
        public val time: Long,
        public val segments: List<MilkyIncomingSegment>,
    )

    public companion object {
        public const val TYPE: String = "reply"
    }
}

/** [image 图片消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-image)。 */
@Serializable
@SerialName(MilkyIncomingImageSegment.TYPE)
public class MilkyIncomingImageSegment
@MilkyApiModelConstructor private constructor(
    public val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("resource_id")
        public val resourceId: String,
        @SerialName("temp_url")
        public val tempUrl: String,
        public val width: Int,
        public val height: Int,
        public val summary: String,
        @SerialName("sub_type")
        public val subType: MilkyImageSubType,
    )

    public companion object {
        public const val TYPE: String = "image"
    }
}

/** [record 语音消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-record)。 */
@Serializable
@SerialName(MilkyIncomingRecordSegment.TYPE)
public class MilkyIncomingRecordSegment
@MilkyApiModelConstructor private constructor(
    public val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("resource_id")
        public val resourceId: String,
        @SerialName("temp_url")
        public val tempUrl: String,
        public val duration: Int,
    )

    public companion object {
        public const val TYPE: String = "record"
    }
}

/** [video 视频消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-video)。 */
@Serializable
@SerialName(MilkyIncomingVideoSegment.TYPE)
public class MilkyIncomingVideoSegment
@MilkyApiModelConstructor private constructor(
    public val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("resource_id")
        public val resourceId: String,
        @SerialName("temp_url")
        public val tempUrl: String,
        public val width: Int,
        public val height: Int,
        public val duration: Int,
    )

    public companion object {
        public const val TYPE: String = "video"
    }
}

/** [file 文件消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-file)。 */
@Serializable
@SerialName(MilkyIncomingFileSegment.TYPE)
public class MilkyIncomingFileSegment
@MilkyApiModelConstructor private constructor(
    public val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("file_id")
        public val fileId: String,
        @SerialName("file_name")
        public val fileName: String,
        @SerialName("file_size")
        public val fileSize: Long,
        @SerialName("file_hash")
        public val fileHash: String? = null,
    )

    public companion object {
        public const val TYPE: String = "file"
    }
}

/** [forward 合并转发消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-forward)。 */
@Serializable
@SerialName(MilkyIncomingForwardSegment.TYPE)
public class MilkyIncomingForwardSegment
@MilkyApiModelConstructor private constructor(
    public val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("forward_id")
        public val forwardId: String,
        public val title: String,
        public val preview: List<String>,
        public val summary: String,
    )

    public companion object {
        public const val TYPE: String = "forward"
    }
}

/** [market_face 市场表情消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-market_face)。 */
@Serializable
@SerialName(MilkyIncomingMarketFaceSegment.TYPE)
public class MilkyIncomingMarketFaceSegment
@MilkyApiModelConstructor private constructor(
    public val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("emoji_package_id")
        public val emojiPackageId: Int,
        @SerialName("emoji_id")
        public val emojiId: String,
        public val key: String,
        public val summary: String,
        public val url: String,
    )

    public companion object {
        public const val TYPE: String = "market_face"
    }
}

/** [light_app 小程序消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-light_app)。 */
@Serializable
@SerialName(MilkyIncomingLightAppSegment.TYPE)
public class MilkyIncomingLightAppSegment
@MilkyApiModelConstructor private constructor(
    public val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("app_name")
        public val appName: String,
        @SerialName("json_payload")
        public val jsonPayload: String,
    )

    public companion object {
        public const val TYPE: String = "light_app"
    }
}

/** [xml XML 消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-xml)。 */
@Serializable
@SerialName(MilkyIncomingXmlSegment.TYPE)
public class MilkyIncomingXmlSegment
@MilkyApiModelConstructor private constructor(
    public val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("service_id")
        public val serviceId: Int,
        @SerialName("xml_payload")
        public val xmlPayload: String,
    )

    public companion object {
        public const val TYPE: String = "xml"
    }
}
