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
import kotlin.jvm.JvmStatic

/**
 * [OutgoingSegment 发送消息段](https://milky.ntqqrev.org/struct/OutgoingSegment)。
 *
 * Milky 消息段使用顶层 `type` 与内部 `data` 的结构。
 */
@Serializable
public sealed interface MilkyOutgoingSegment {
    public companion object {
        /** 构建文本消息段。 */
        @JvmStatic
        public fun text(text: String): MilkyOutgoingTextSegment =
            MilkyOutgoingTextSegment(MilkyOutgoingTextSegment.Data(text))

        /** 构建提及消息段。 */
        @JvmStatic
        public fun mention(userId: Long): MilkyOutgoingMentionSegment =
            MilkyOutgoingMentionSegment(MilkyOutgoingMentionSegment.Data(userId))

        /** 构建提及全体消息段。 */
        @JvmStatic
        public fun mentionAll(): MilkyOutgoingMentionAllSegment =
            MilkyOutgoingMentionAllSegment(MilkyOutgoingMentionAllSegment.Data())

        /** 构建表情消息段。 */
        @JvmStatic
        public fun face(faceId: String, isLarge: Boolean = false): MilkyOutgoingFaceSegment =
            MilkyOutgoingFaceSegment(MilkyOutgoingFaceSegment.Data(faceId, isLarge))

        /** 构建回复消息段。 */
        @JvmStatic
        public fun reply(messageSeq: Long): MilkyOutgoingReplySegment =
            MilkyOutgoingReplySegment(MilkyOutgoingReplySegment.Data(messageSeq))

        /** 构建图片消息段。 */
        @JvmStatic
        public fun image(
            uri: String,
            subType: MilkyImageSubType = MilkyImageSubType.NORMAL,
            summary: String? = null,
        ): MilkyOutgoingImageSegment =
            MilkyOutgoingImageSegment(MilkyOutgoingImageSegment.Data(uri, subType, summary))

        /** 构建语音消息段。 */
        @JvmStatic
        public fun record(uri: String): MilkyOutgoingRecordSegment =
            MilkyOutgoingRecordSegment(MilkyOutgoingRecordSegment.Data(uri))

        /** 构建视频消息段。 */
        @JvmStatic
        public fun video(uri: String, thumbUri: String? = null): MilkyOutgoingVideoSegment =
            MilkyOutgoingVideoSegment(MilkyOutgoingVideoSegment.Data(uri, thumbUri))

        /** 构建合并转发消息段。 */
        @JvmStatic
        public fun forward(
            messages: List<MilkyOutgoingForwardedMessage>,
            title: String? = null,
            preview: List<String>? = null,
            summary: String? = null,
            prompt: String? = null,
        ): MilkyOutgoingForwardSegment =
            MilkyOutgoingForwardSegment(MilkyOutgoingForwardSegment.Data(messages, title, preview, summary, prompt))

        /** 构建小程序消息段。 */
        @JvmStatic
        public fun lightApp(jsonPayload: String): MilkyOutgoingLightAppSegment =
            MilkyOutgoingLightAppSegment(MilkyOutgoingLightAppSegment.Data(jsonPayload))
    }
}

/** [text 文本消息段](https://milky.ntqqrev.org/struct/OutgoingSegment#type-text)。 */
@Serializable
@SerialName(MilkyOutgoingTextSegment.TYPE)
public data class MilkyOutgoingTextSegment
@MilkyApiModelConstructor constructor(
    val data: Data,
) : MilkyOutgoingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor constructor(
        val text: String,
    )

    public companion object {
        public const val TYPE: String = "text"
    }
}

/** [mention 提及消息段](https://milky.ntqqrev.org/struct/OutgoingSegment#type-mention)。 */
@Serializable
@SerialName(MilkyOutgoingMentionSegment.TYPE)
public data class MilkyOutgoingMentionSegment
@MilkyApiModelConstructor constructor(
    val data: Data,
) : MilkyOutgoingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor constructor(
        @SerialName("user_id")
        val userId: Long,
    )

    public companion object {
        public const val TYPE: String = "mention"
    }
}

/** [mention_all 提及全体消息段](https://milky.ntqqrev.org/struct/OutgoingSegment#type-mention_all)。 */
@Serializable
@SerialName(MilkyOutgoingMentionAllSegment.TYPE)
public data class MilkyOutgoingMentionAllSegment
@MilkyApiModelConstructor constructor(
    val data: Data,
) : MilkyOutgoingSegment {
    @Serializable
    public class Data
    @MilkyApiModelConstructor constructor()

    public companion object {
        public const val TYPE: String = "mention_all"
    }
}

/** [face 表情消息段](https://milky.ntqqrev.org/struct/OutgoingSegment#type-face)。 */
@Serializable
@SerialName(MilkyOutgoingFaceSegment.TYPE)
public data class MilkyOutgoingFaceSegment
@MilkyApiModelConstructor constructor(
    val data: Data,
) : MilkyOutgoingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor constructor(
        @SerialName("face_id")
        val faceId: String,
        @SerialName("is_large")
        val isLarge: Boolean = false,
    )

    public companion object {
        public const val TYPE: String = "face"
    }
}

/** [reply 回复消息段](https://milky.ntqqrev.org/struct/OutgoingSegment#type-reply)。 */
@Serializable
@SerialName(MilkyOutgoingReplySegment.TYPE)
public data class MilkyOutgoingReplySegment
@MilkyApiModelConstructor constructor(
    val data: Data,
) : MilkyOutgoingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor constructor(
        @SerialName("message_seq")
        val messageSeq: Long,
    )

    public companion object {
        public const val TYPE: String = "reply"
    }
}

/** [image 图片消息段](https://milky.ntqqrev.org/struct/OutgoingSegment#type-image)。 */
@Serializable
@SerialName(MilkyOutgoingImageSegment.TYPE)
public data class MilkyOutgoingImageSegment
@MilkyApiModelConstructor constructor(
    val data: Data,
) : MilkyOutgoingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor constructor(
        val uri: String,
        @SerialName("sub_type")
        val subType: MilkyImageSubType = MilkyImageSubType.NORMAL,
        val summary: String? = null,
    )

    public companion object {
        public const val TYPE: String = "image"
    }
}

/** [record 语音消息段](https://milky.ntqqrev.org/struct/OutgoingSegment#type-record)。 */
@Serializable
@SerialName(MilkyOutgoingRecordSegment.TYPE)
public data class MilkyOutgoingRecordSegment
@MilkyApiModelConstructor constructor(
    val data: Data,
) : MilkyOutgoingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor constructor(
        val uri: String,
    )

    public companion object {
        public const val TYPE: String = "record"
    }
}

/** [video 视频消息段](https://milky.ntqqrev.org/struct/OutgoingSegment#type-video)。 */
@Serializable
@SerialName(MilkyOutgoingVideoSegment.TYPE)
public data class MilkyOutgoingVideoSegment
@MilkyApiModelConstructor constructor(
    val data: Data,
) : MilkyOutgoingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor constructor(
        val uri: String,
        @SerialName("thumb_uri")
        val thumbUri: String? = null,
    )

    public companion object {
        public const val TYPE: String = "video"
    }
}

/** [forward 合并转发消息段](https://milky.ntqqrev.org/struct/OutgoingSegment#type-forward)。 */
@Serializable
@SerialName(MilkyOutgoingForwardSegment.TYPE)
public data class MilkyOutgoingForwardSegment
@MilkyApiModelConstructor constructor(
    val data: Data,
) : MilkyOutgoingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor constructor(
        val messages: List<MilkyOutgoingForwardedMessage>,
        val title: String? = null,
        val preview: List<String>? = null,
        val summary: String? = null,
        val prompt: String? = null,
    )

    public companion object {
        public const val TYPE: String = "forward"
    }
}

/** [light_app 小程序消息段](https://milky.ntqqrev.org/struct/OutgoingSegment#type-light_app)。 */
@Serializable
@SerialName(MilkyOutgoingLightAppSegment.TYPE)
public data class MilkyOutgoingLightAppSegment
@MilkyApiModelConstructor constructor(
    val data: Data,
) : MilkyOutgoingSegment {
    @Serializable
    public data class Data
    @MilkyApiModelConstructor constructor(
        @SerialName("json_payload")
        val jsonPayload: String,
    )

    public companion object {
        public const val TYPE: String = "light_app"
    }
}
