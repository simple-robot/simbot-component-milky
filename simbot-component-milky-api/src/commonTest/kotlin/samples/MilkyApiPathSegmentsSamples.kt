package samples

import io.ktor.http.*
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 *
 * @author Forte Scarlet
 */
class MilkyApiPathSegmentsSamples {
    @Test
    fun ktorBuildUrlViaTakeFromSample() {
        with(URLBuilder("https://example.com/milky/api")) {
            takeFrom("api/send_private_message")
            assertEquals("https://example.com/milky/api/send_private_message", this.buildString())
        }
        with(URLBuilder("https://example.com/milky/api/")) {
            takeFrom("api/send_private_message")
            assertEquals("https://example.com/milky/api/api/send_private_message", this.buildString())
        }
        with(URLBuilder("https://example.com/milky/api")) {
            takeFrom("/api/send_private_message")
            assertEquals("https://example.com/api/send_private_message", this.buildString())
        }
        with(URLBuilder("https://example.com/milky/api/")) {
            takeFrom("/api/send_private_message")
            assertEquals("https://example.com/api/send_private_message", this.buildString())
        }
    }

}
