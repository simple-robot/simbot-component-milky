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

import kotlinx.serialization.KSerializer
import love.forte.simbot.milky.api.MilkyApi
import love.forte.simbot.milky.api.SerializationBasedTypedMilkyApi
import love.forte.simbot.milky.model.api.MilkyApiResult
import love.forte.simbot.milky.model.api.group.MilkyGetGroupNotificationsParam
import love.forte.simbot.milky.model.api.group.MilkyGetGroupNotificationsResponse
import kotlin.jvm.JvmStatic

/**
 * [get_group_notifications 获取群通知列表](https://milky.ntqqrev.org/api/group#get_group_notifications)
 *
 * @author Forte Scarlet
 */
public class MilkyGetGroupNotificationsApi private constructor(
    private val param: MilkyGetGroupNotificationsParam
) :
    SerializationBasedTypedMilkyApi<MilkyGetGroupNotificationsResponse>(),
    MilkyGroupApi {
    override val apiName: String
        get() = API_NAME

    override val contentSerializer: KSerializer<MilkyGetGroupNotificationsResponse>
        get() = MilkyGetGroupNotificationsResponse.serializer()

    override val resultSerializer: KSerializer<MilkyApiResult<MilkyGetGroupNotificationsResponse>>
        get() = RESULT_DESERIALIZER

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyGetGroupNotificationsParam.serializer(), param)

    override fun toString(): String {
        return "MilkyGetGroupNotificationsApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "get_group_notifications"
        private val RESULT_DESERIALIZER = MilkyApiResult.serializer(MilkyGetGroupNotificationsResponse.serializer())

        private val EMPTY_INSTANCE = MilkyGetGroupNotificationsApi(MilkyGetGroupNotificationsParam())

        /**
         * 使用 [MilkyGetGroupNotificationsParam] 构建 [MilkyGetGroupNotificationsApi]。
         */
        @JvmStatic
        public fun create(param: MilkyGetGroupNotificationsParam): MilkyGetGroupNotificationsApi =
            MilkyGetGroupNotificationsApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyGetGroupNotificationsApi]。
         * @param startNotificationSeq 起始通知序列号
         * @param isFiltered true 表示只获取被过滤的通知，false 表示只获取未被过滤的通知
         * @param limit 获取的最大通知数量
         */
        @JvmStatic
        public fun create(
            startNotificationSeq: Long? = null,
            isFiltered: Boolean = false,
            limit: Int = 20
        ): MilkyGetGroupNotificationsApi =
            create(MilkyGetGroupNotificationsParam(startNotificationSeq, isFiltered, limit))

        /**
         * 使用 API 入参字段构建 [MilkyGetGroupNotificationsApi]。
         */
        @JvmStatic
        public fun create(): MilkyGetGroupNotificationsApi = EMPTY_INSTANCE
    }
}
