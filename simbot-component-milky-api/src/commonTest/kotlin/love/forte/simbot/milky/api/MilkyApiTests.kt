package love.forte.simbot.milky.api

import io.ktor.http.*
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 *
 * @author Forte Scarlet
 */
class MilkyApiTests {

    @Test
    fun testPathSegmentsBuild() {
        with(URLBuilder("https://example.com/")) {
            takeFrom("api/send_private_message")
            assertEquals("https://example.com/api/send_private_message", buildString())
            assertEquals("https://example.com/api/send_private_message", build().toString())
        }
        with(URLBuilder("https://example.com")) {
            takeFrom("api/send_private_message")
            assertEquals("https://example.com/api/send_private_message", buildString())
            assertEquals("https://example.com/api/send_private_message", build().toString())
        }
        with(URLBuilder("https://example.com/")) {
            takeFrom("/api/send_private_message")
            assertEquals("https://example.com/api/send_private_message", buildString())
            assertEquals("https://example.com/api/send_private_message", build().toString())
        }
        with(URLBuilder("https://example.com")) {
            takeFrom("/api/send_private_message")
            assertEquals("https://example.com/api/send_private_message", buildString())
            assertEquals("https://example.com/api/send_private_message", build().toString())
        }

        with(URLBuilder("https://example.com/milky/api")) {
            takeFrom("api/send_private_message")
            assertEquals("https://example.com/milky/api/send_private_message", buildString())
            assertEquals("https://example.com/milky/api/send_private_message", build().toString())
        }
        with(URLBuilder("https://example.com/milky/api/")) {
            takeFrom("api/send_private_message")
            assertEquals("https://example.com/milky/api/api/send_private_message", buildString())
            assertEquals("https://example.com/milky/api/api/send_private_message", build().toString())
        }
        with(URLBuilder("https://example.com/milky/api")) {
            takeFrom("/api/send_private_message")
            assertEquals("https://example.com/api/send_private_message", buildString())
            assertEquals("https://example.com/api/send_private_message", build().toString())
        }
        with(URLBuilder("https://example.com/milky/api/")) {
            takeFrom("/api/send_private_message")
            assertEquals("https://example.com/api/send_private_message", buildString())
            assertEquals("https://example.com/api/send_private_message", build().toString())
        }
    }

}
