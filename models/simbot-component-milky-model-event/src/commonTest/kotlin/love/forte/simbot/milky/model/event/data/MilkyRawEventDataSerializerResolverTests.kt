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

package love.forte.simbot.milky.model.event.data

import love.forte.simbot.milky.model.event.data.MilkyRawEventDataSerializerResolver.milkyRawEventDataSerializersMap
import love.forte.simbot.milky.model.event.data.MilkyRawEventDataSerializerResolver.resolveMilkyRawEventDataSerializer
import love.forte.simbot.milky.model.event.data.friend.MilkyRawFriendFileUploadEventData
import love.forte.simbot.milky.model.event.data.group.MilkyRawGroupInvitationEventData
import kotlin.test.*

/**
 * 覆盖 event data serializer resolver 的 KSP 生成逻辑。
 *
 * 测试重点：
 * - 生成的 map 暴露 event_type 到 serializer 的静态映射。
 * - 普通事件类型可以解析到对应的具体事件 data serializer。
 * - `incoming_message` 是处理器刻意跳过的特殊事件类型，应保持为 `null`。
 * - 未知事件类型不应误解析到任意 serializer。
 */
class MilkyRawEventDataSerializerResolverTests {
    @Test
    fun exposeMarkedEventDataSerializersMap() {
        // map 是额外暴露的静态索引；resolver 函数仍由处理器生成独立 when 分支。
        assertTrue("friend_file_upload" in milkyRawEventDataSerializersMap)
        assertTrue("group_invitation" in milkyRawEventDataSerializersMap)
        assertFalse("incoming_message" in milkyRawEventDataSerializersMap)
        assertEquals(
            MilkyRawFriendFileUploadEventData.SERIAL_NAME,
            milkyRawEventDataSerializersMap.getValue("friend_file_upload").descriptor.serialName
        )
    }

    @Test
    fun resolveMarkedEventDataSerializers() {
        val friendFileUpload = assertNotNull(resolveMilkyRawEventDataSerializer("friend_file_upload"))
        assertEquals(MilkyRawFriendFileUploadEventData.SERIAL_NAME, friendFileUpload.descriptor.serialName)

        val groupInvitation = assertNotNull(resolveMilkyRawEventDataSerializer("group_invitation"))
        assertEquals(MilkyRawGroupInvitationEventData.SERIAL_NAME, groupInvitation.descriptor.serialName)
    }

    @Test
    fun keepSpecialAndUnknownEventTypesUnresolved() {
        // `incoming_message` 还需要再根据 message_scene 拆分，不参与 event_type 的直接映射。
        assertNull(resolveMilkyRawEventDataSerializer("incoming_message"))

        // 未知事件类型必须保持为空，避免把错误 payload 解成错误的事件 data。
        assertNull(resolveMilkyRawEventDataSerializer("unknown_event"))
    }
}
