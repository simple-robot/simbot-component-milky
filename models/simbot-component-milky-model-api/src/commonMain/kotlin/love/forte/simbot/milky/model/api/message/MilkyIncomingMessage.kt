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
import love.forte.simbot.milky.model.api.MilkyApiResponseModel
import love.forte.simbot.milky.model.entity.friend.MilkyRawFriendEntity
import love.forte.simbot.milky.model.entity.group.MilkyRawGroupEntity
import love.forte.simbot.milky.model.entity.group.MilkyRawGroupMemberEntity

/**
 * [IncomingMessage 接收消息](https://milky.ntqqrev.org/struct/IncomingMessage)
 */
@Serializable
public data class MilkyIncomingMessage
@MilkyApiModelConstructor private constructor(
    /**
     * 消息场景
     */
    @SerialName("message_scene")
    val messageScene: MilkyMessageScene,
    /**
     * 好友 QQ 号或群号
     */
    @SerialName("peer_id")
    val peerId: Long,
    /**
     * 消息序列号
     */
    @SerialName("message_seq")
    val messageSeq: Long,
    /**
     * 发送者 QQ 号
     */
    @SerialName("sender_id")
    val senderId: Long,
    /**
     * 消息 Unix 时间戳（秒）
     */
    val time: Long,
    /**
     * 消息段列表
     */
    val segments: List<MilkyIncomingSegment>,
    /**
     * 好友信息
     */
    val friend: MilkyRawFriendEntity? = null,
    /**
     * 群信息
     */
    val group: MilkyRawGroupEntity? = null,
    /**
     * 群成员信息
     */
    @SerialName("group_member")
    val groupMember: MilkyRawGroupMemberEntity? = null,
) : MilkyApiResponseModel
