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
import love.forte.simbot.milky.model.api.group.MilkySetGroupMemberMuteParam
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.time.Duration

/**
 * [set_group_member_mute 设置群成员禁言](https://milky.ntqqrev.org/api/group#set_group_member_mute)
 *
 * @author Forte Scarlet
 */
public class MilkySetGroupMemberMuteApi private constructor(
    private val param: MilkySetGroupMemberMuteParam
) : UnitResultMilkyApi(), MilkyGroupApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkySetGroupMemberMuteParam.serializer(), param)

    override fun toString(): String {
        return "MilkySetGroupMemberMuteApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "set_group_member_mute"

        /**
         * 使用 [MilkySetGroupMemberMuteParam] 构建 [MilkySetGroupMemberMuteApi]。
         */
        @JvmStatic
        public fun create(param: MilkySetGroupMemberMuteParam): MilkySetGroupMemberMuteApi =
            MilkySetGroupMemberMuteApi(param)

        /**
         * 使用 API 入参字段构建 [MilkySetGroupMemberMuteApi]。
         * @param groupId 群号
         * @param userId 被设置的 QQ 号
         * @param duration 禁言持续时间（秒），设为 0 为取消禁言
         */
        @JvmStatic
        @JvmOverloads
        public fun create(groupId: Long, userId: Long, duration: Int = 0): MilkySetGroupMemberMuteApi =
            create(MilkySetGroupMemberMuteParam(groupId, userId, duration))
    }
}

/**
 * 使用 API 入参字段构建 [MilkySetGroupMemberMuteApi]。
 * @param groupId 群号
 * @param userId 被设置的 QQ 号
 * @param duration 禁言持续时间（秒），设为 0 为取消禁言
 */
public fun MilkySetGroupMemberMuteApi.Companion.create(
    groupId: Long, userId: Long, duration: Duration
): MilkySetGroupMemberMuteApi =
    MilkySetGroupMemberMuteApi.create(groupId, userId, duration.inWholeSeconds.toInt())
