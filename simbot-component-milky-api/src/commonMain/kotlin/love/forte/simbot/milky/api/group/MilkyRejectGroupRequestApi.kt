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
import love.forte.simbot.milky.model.api.group.MilkyGroupRequestNotificationType
import love.forte.simbot.milky.model.api.group.MilkyRejectGroupRequestParam
import kotlin.jvm.JvmStatic

/**
 * [reject_group_request 拒绝入群/邀请他人入群请求](https://milky.ntqqrev.org/api/group#reject_group_request)
 *
 * @author Forte Scarlet
 */
public class MilkyRejectGroupRequestApi private constructor(
    private val param: MilkyRejectGroupRequestParam
) : UnitResultMilkyApi(), MilkyGroupApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyRejectGroupRequestParam.serializer(), param)

    override fun toString(): String {
        return "MilkyRejectGroupRequestApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "reject_group_request"

        /**
         * 使用 [MilkyRejectGroupRequestParam] 构建 [MilkyRejectGroupRequestApi]。
         */
        @JvmStatic
        public fun create(param: MilkyRejectGroupRequestParam): MilkyRejectGroupRequestApi =
            MilkyRejectGroupRequestApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyRejectGroupRequestApi]。
         * @param notificationSeq 请求对应的通知序列号
         * @param notificationType 请求对应的通知类型
         * @param groupId 请求所在的群号
         * @param isFiltered 是否是被过滤的请求
         * @param reason 拒绝理由
         */
        @JvmStatic
        public fun create(
            notificationSeq: Long,
            notificationType: MilkyGroupRequestNotificationType,
            groupId: Long,
            isFiltered: Boolean = false,
            reason: String? = null
        ): MilkyRejectGroupRequestApi =
            create(MilkyRejectGroupRequestParam(notificationSeq, notificationType, groupId, isFiltered, reason))
    }
}
