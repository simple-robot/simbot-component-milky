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
public data class MilkyIncomingTextSegment
@MilkyApiModelConstructor private constructor(
    val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor private constructor(
        val text: String,
    )

    public companion object {
        public const val TYPE: String = "text"
    }
}

/** [mention 提及消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-mention)。 */
@Serializable
@SerialName(MilkyIncomingMentionSegment.TYPE)
public data class MilkyIncomingMentionSegment
@MilkyApiModelConstructor private constructor(
    val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("user_id")
        val userId: Long,
        val name: String,
    )

    public companion object {
        public const val TYPE: String = "mention"
    }
}

/** [mention_all 提及全体消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-mention_all)。 */
@Serializable
@SerialName(MilkyIncomingMentionAllSegment.TYPE)
public data class MilkyIncomingMentionAllSegment
@MilkyApiModelConstructor private constructor(
    val data: Data,
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
public data class MilkyIncomingFaceSegment
@MilkyApiModelConstructor private constructor(
    val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("face_id")
        val faceId: String,
        @SerialName("is_large")
        val isLarge: Boolean,
    )

    public companion object {
        public const val TYPE: String = "face"
    }
}

/** [reply 回复消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-reply)。 */
@Serializable
@SerialName(MilkyIncomingReplySegment.TYPE)
public data class MilkyIncomingReplySegment
@MilkyApiModelConstructor private constructor(
    val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("message_seq")
        val messageSeq: Long,
        @SerialName("sender_id")
        val senderId: Long,
        @SerialName("sender_name")
        val senderName: String? = null,
        val time: Long,
        val segments: List<MilkyIncomingSegment>,
    )

    public companion object {
        public const val TYPE: String = "reply"
    }
}

/** [image 图片消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-image)。 */
@Serializable
@SerialName(MilkyIncomingImageSegment.TYPE)
public data class MilkyIncomingImageSegment
@MilkyApiModelConstructor private constructor(
    val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("resource_id")
        val resourceId: String,
        @SerialName("temp_url")
        val tempUrl: String,
        val width: Int,
        val height: Int,
        val summary: String,
        @SerialName("sub_type")
        val subType: MilkyImageSubType,
    )

    public companion object {
        public const val TYPE: String = "image"
    }
}

/** [record 语音消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-record)。 */
@Serializable
@SerialName(MilkyIncomingRecordSegment.TYPE)
public data class MilkyIncomingRecordSegment
@MilkyApiModelConstructor private constructor(
    val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("resource_id")
        val resourceId: String,
        @SerialName("temp_url")
        val tempUrl: String,
        val duration: Int,
    )

    public companion object {
        public const val TYPE: String = "record"
    }
}

/** [video 视频消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-video)。 */
@Serializable
@SerialName(MilkyIncomingVideoSegment.TYPE)
public data class MilkyIncomingVideoSegment
@MilkyApiModelConstructor private constructor(
    val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("resource_id")
        val resourceId: String,
        @SerialName("temp_url")
        val tempUrl: String,
        val width: Int,
        val height: Int,
        val duration: Int,
    )

    public companion object {
        public const val TYPE: String = "video"
    }
}

/** [file 文件消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-file)。 */
@Serializable
@SerialName(MilkyIncomingFileSegment.TYPE)
public data class MilkyIncomingFileSegment
@MilkyApiModelConstructor private constructor(
    val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("file_id")
        val fileId: String,
        @SerialName("file_name")
        val fileName: String,
        @SerialName("file_size")
        val fileSize: Long,
        @SerialName("file_hash")
        val fileHash: String? = null,
    )

    public companion object {
        public const val TYPE: String = "file"
    }
}

/** [forward 合并转发消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-forward)。 */
@Serializable
@SerialName(MilkyIncomingForwardSegment.TYPE)
public data class MilkyIncomingForwardSegment
@MilkyApiModelConstructor private constructor(
    val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("forward_id")
        val forwardId: String,
        val title: String,
        val preview: List<String>,
        val summary: String,
    )

    public companion object {
        public const val TYPE: String = "forward"
    }
}

/** [market_face 市场表情消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-market_face)。 */
@Serializable
@SerialName(MilkyIncomingMarketFaceSegment.TYPE)
public data class MilkyIncomingMarketFaceSegment
@MilkyApiModelConstructor private constructor(
    val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("emoji_package_id")
        val emojiPackageId: Int,
        @SerialName("emoji_id")
        val emojiId: String,
        val key: String,
        val summary: String,
        val url: String,
    )

    public companion object {
        public const val TYPE: String = "market_face"
    }
}

/** [light_app 小程序消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-light_app)。 */
@Serializable
@SerialName(MilkyIncomingLightAppSegment.TYPE)
public data class MilkyIncomingLightAppSegment
@MilkyApiModelConstructor private constructor(
    val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("app_name")
        val appName: String,
        @SerialName("json_payload")
        val jsonPayload: String,
    )

    public companion object {
        public const val TYPE: String = "light_app"
    }
}

/** [xml XML 消息段](https://milky.ntqqrev.org/struct/IncomingSegment#type-xml)。 */
@Serializable
@SerialName(MilkyIncomingXmlSegment.TYPE)
public data class MilkyIncomingXmlSegment
@MilkyApiModelConstructor private constructor(
    val data: Data,
) : MilkyIncomingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor private constructor(
        @SerialName("service_id")
        val serviceId: Int,
        @SerialName("xml_payload")
        val xmlPayload: String,
    )

    public companion object {
        public const val TYPE: String = "xml"
    }
}
