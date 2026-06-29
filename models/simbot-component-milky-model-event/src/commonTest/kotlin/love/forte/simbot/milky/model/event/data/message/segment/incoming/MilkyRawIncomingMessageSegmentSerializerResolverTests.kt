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

import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.json.Json
import kotlin.test.*

/**
 * 覆盖 IncomingSegment serializer resolver 的 KSP 生成逻辑。
 *
 * 测试重点：
 * - 生成的 map 暴露 type 到 serializer 的静态映射。
 * - Milky 文档中全部 13 个接收消息段 type 都能解析到具体 serializer。
 * - serializer 的 serialName 与协议 discriminator 值一致，确保 `type` 映射没有串线。
 * - 未知 type 不应被解析，调用方可以据此自行降级或报错。
 */
class MilkyRawIncomingMessageSegmentSerializerResolverTests {
    private val expectedTypes = listOf(
        "text",
        "mention",
        "mention_all",
        "face",
        "reply",
        "image",
        "record",
        "video",
        "file",
        "forward",
        "market_face",
        "light_app",
        "xml",
    )

    private val json = Json {
        serializersModule = MilkyRawIncomingMessageSegment.serializersModules()
        ignoreUnknownKeys = true
    }

    private val segmentSerializer = PolymorphicSerializer(MilkyRawIncomingMessageSegment::class)

    private fun decodeSegment(value: String): MilkyRawIncomingMessageSegment {
        return json.decodeFromString(segmentSerializer, value)
    }

    @Test
    fun exposeAllIncomingSegmentTypeSerializersMap() {
        // map 是额外暴露的静态索引；resolver 函数仍由处理器生成独立 when 分支。
        assertEquals(
            expectedTypes.toSet(),
            MilkyRawIncomingMessageSegmentSerializerResolver.milkyRawIncomingMessageSegmentSerializersMap.keys
        )
        for (type in expectedTypes) {
            assertEquals(
                type,
                MilkyRawIncomingMessageSegmentSerializerResolver.milkyRawIncomingMessageSegmentSerializersMap
                    .getValue(type)
                    .descriptor
                    .serialName
            )
        }
    }

    @Test
    fun resolveAllIncomingSegmentTypeSerializers() {
        for (type in expectedTypes) {
            val serializer = assertNotNull(
                MilkyRawIncomingMessageSegmentSerializerResolver.resolveMilkyRawIncomingMessageSegmentSerializer(type)
            )
            assertEquals(type, serializer.descriptor.serialName)
        }
    }

    @Test
    fun keepUnknownIncomingSegmentTypeUnresolved() {
        // 未知消息段 type 不能落到任意已有分支，避免错误解释 data 字段。
        assertNull(MilkyRawIncomingMessageSegmentSerializerResolver.resolveMilkyRawIncomingMessageSegmentSerializer("unknown_segment"))
    }

    @Test
    fun deserializePrimitiveIncomingSegmentsByType() {
        // 这组用例验证多态反序列化会读取顶层 `type`，并把 `data` 解成对应分支的数据类型。
        val text = decodeSegment("""{"type":"text","data":{"text":"hello"}}""")
        assertTrue(text is MilkyRawIncomingTextMessageSegment)
        assertEquals("hello", text.data.text)

        val mention = decodeSegment("""{"type":"mention","data":{"user_id":123456,"name":"forte"}}""")
        assertTrue(mention is MilkyRawIncomingMentionMessageSegment)
        assertEquals(123456L, mention.data.userId)
        assertEquals("forte", mention.data.name)

        val mentionAll = decodeSegment("""{"type":"mention_all","data":{}}""")
        assertTrue(mentionAll is MilkyRawIncomingMentionAllMessageSegment)

        val face = decodeSegment("""{"type":"face","data":{"face_id":"66","is_large":true}}""")
        assertTrue(face is MilkyRawIncomingFaceMessageSegment)
        assertEquals("66", face.data.faceId)
        assertEquals(true, face.data.isLarge)
    }

    @Test
    fun deserializeMediaIncomingSegmentsByType() {
        // 这组用例覆盖带 snake_case 字段、枚举字段、列表字段和可空字段的消息段。
        val image = decodeSegment(
            """
            {
              "type": "image",
              "data": {
                "resource_id": "image-resource",
                "temp_url": "https://example.test/image.png",
                "width": 320,
                "height": 240,
                "summary": "[图片]",
                "sub_type": "sticker"
              }
            }
            """.trimIndent()
        )
        assertTrue(image is MilkyRawIncomingImageMessageSegment)
        assertEquals("image-resource", image.data.resourceId)
        assertEquals(MilkyRawIncomingImageSubType.STICKER, image.data.subType)

        val file = decodeSegment(
            """
            {
              "type": "file",
              "data": {
                "file_id": "file-resource",
                "file_name": "report.txt",
                "file_size": 1024
              }
            }
            """.trimIndent()
        )
        assertTrue(file is MilkyRawIncomingFileMessageSegment)
        assertEquals("report.txt", file.data.fileName)
        assertNull(file.data.fileHash)

        val forward = decodeSegment(
            """
            {
              "type": "forward",
              "data": {
                "forward_id": "forward-resource",
                "title": "聊天记录",
                "preview": ["A: hello", "B: hi"],
                "summary": "2 条消息"
              }
            }
            """.trimIndent()
        )
        assertTrue(forward is MilkyRawIncomingForwardMessageSegment)
        assertEquals(listOf("A: hello", "B: hi"), forward.data.preview)
    }

    @Test
    fun deserializeNestedReplyIncomingSegmentByType() {
        // reply.data.segments 是嵌套的 IncomingSegment 列表，能覆盖递归多态反序列化。
        val reply = decodeSegment(
            """
            {
              "type": "reply",
              "data": {
                "message_seq": 42,
                "sender_id": 10001,
                "sender_name": "sender",
                "time": 1710000000,
                "segments": [
                  {"type": "text", "data": {"text": "quoted"}}
                ]
              }
            }
            """.trimIndent()
        )

        assertTrue(reply is MilkyRawIncomingReplyMessageSegment)
        assertEquals(42L, reply.data.messageSeq)
        assertEquals("sender", reply.data.senderName)

        val quoted = reply.data.segments.single()
        assertTrue(quoted is MilkyRawIncomingTextMessageSegment)
        assertEquals("quoted", quoted.data.text)
    }
}
