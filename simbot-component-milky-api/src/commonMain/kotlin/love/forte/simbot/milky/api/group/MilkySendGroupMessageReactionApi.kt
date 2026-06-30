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

package love.forte.simbot.milky.api.group

import love.forte.simbot.milky.api.MilkyApi
import love.forte.simbot.milky.api.UnitResultMilkyApi
import love.forte.simbot.milky.model.api.group.MilkyGroupMessageReactionType
import love.forte.simbot.milky.model.api.group.MilkySendGroupMessageReactionParam
import kotlin.jvm.JvmStatic

/**
 * [send_group_message_reaction 发送群消息表情回应](https://milky.ntqqrev.org/api/group#send_group_message_reaction)
 *
 * @author Forte Scarlet
 */
public class MilkySendGroupMessageReactionApi private constructor(
    private val param: MilkySendGroupMessageReactionParam
) : UnitResultMilkyApi(), MilkyGroupApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkySendGroupMessageReactionParam.serializer(), param)

    override fun toString(): String {
        return "MilkySendGroupMessageReactionApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "send_group_message_reaction"

        /**
         * 使用 [MilkySendGroupMessageReactionParam] 构建 [MilkySendGroupMessageReactionApi]。
         */
        @JvmStatic
        public fun create(param: MilkySendGroupMessageReactionParam): MilkySendGroupMessageReactionApi =
            MilkySendGroupMessageReactionApi(param)

        /**
         * 使用 API 入参字段构建 [MilkySendGroupMessageReactionApi]。
         * @param groupId 群号
         * @param messageSeq 要回应的消息序列号
         * @param reaction 发送的回应的表情 ID
         * @param reactionType 发送的回应类型
         * @param isAdd 是否添加表情，false 表示取消
         */
        @JvmStatic
        public fun create(
            groupId: Long,
            messageSeq: Long,
            reaction: String,
            reactionType: MilkyGroupMessageReactionType = MilkyGroupMessageReactionType.FACE,
            isAdd: Boolean = true
        ): MilkySendGroupMessageReactionApi =
            create(MilkySendGroupMessageReactionParam(groupId, messageSeq, reaction, reactionType, isAdd))
    }
}
