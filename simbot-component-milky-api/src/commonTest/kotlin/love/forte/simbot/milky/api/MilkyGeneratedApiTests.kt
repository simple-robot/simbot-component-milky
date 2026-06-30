package love.forte.simbot.milky.api

import kotlinx.serialization.json.*
import love.forte.simbot.milky.api.group.MilkySendGroupMessageReactionApi
import love.forte.simbot.milky.api.message.MilkyGetForwardedMessagesApi
import love.forte.simbot.milky.api.message.MilkySendPrivateMessageApi
import love.forte.simbot.milky.api.system.MilkyGetImplInfoApi
import love.forte.simbot.milky.model.api.message.MilkyIncomingTextSegment
import love.forte.simbot.milky.model.api.message.MilkyOutgoingSegment
import love.forte.simbot.milky.model.api.system.MilkyQqProtocolType
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class MilkyGeneratedApiTests {
    @Test
    fun encodeOutgoingSegmentWithTypeAndData() {
        val api = MilkySendPrivateMessageApi.create(
            userId = 123456L,
            message = listOf(MilkyOutgoingSegment.text("hello"))
        )

        val body = Json.parseToJsonElement(api.bodyContent()).jsonObject

        assertEquals(123456L, body.long("user_id"))
        val segment = body.getValue("message").jsonArray.single().jsonObject
        assertEquals("text", segment.string("type"))
        assertEquals("hello", segment.getValue("data").jsonObject.string("text"))
    }

    @Test
    fun encodeSnakeCaseEnumDefaults() {
        val api = MilkySendGroupMessageReactionApi.create(
            groupId = 123456L,
            messageSeq = 1000L,
            reaction = "66",
        )

        val body = Json.parseToJsonElement(api.bodyContent()).jsonObject

        assertEquals("face", body.string("reaction_type"))
        assertEquals(true, body.getValue("is_add").jsonPrimitive.boolean)
    }

    @Test
    fun decodeSystemResponse() {
        val result = MilkyGetImplInfoApi.instance().decodeResult(
            """
            {
              "status": "ok",
              "retcode": 0,
              "data": {
                "impl_name": "example",
                "impl_version": "1.0.0",
                "qq_protocol_version": "nt",
                "qq_protocol_type": "linux",
                "milky_version": "1.2"
              }
            }
            """.trimIndent()
        )

        assertEquals(MilkyQqProtocolType.LINUX, result.data?.qqProtocolType)
    }

    @Test
    fun decodeIncomingSegmentInResponse() {
        val content = MilkyGetForwardedMessagesApi.create("forward-id").decodeResultContent(
            """
            {
              "messages": [
                {
                  "message_seq": 42,
                  "sender_name": "sender",
                  "avatar_url": "https://example.test/avatar.png",
                  "time": 1710000000,
                  "segments": [
                    {"type": "text", "data": {"text": "quoted"}}
                  ]
                }
              ]
            }
            """.trimIndent()
        )

        val text = assertIs<MilkyIncomingTextSegment>(content.messages.single().segments.single())
        assertEquals("quoted", text.data.text)
    }

    private fun JsonObject.string(name: String): String = getValue(name).jsonPrimitive.content

    private fun JsonObject.long(name: String): Long = getValue(name).jsonPrimitive.content.toLong()
}
