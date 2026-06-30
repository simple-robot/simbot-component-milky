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
import love.forte.simbot.milky.model.api.group.MilkySendGroupAnnouncementParam
import kotlin.jvm.JvmStatic

/**
 * [send_group_announcement 发送群公告](https://milky.ntqqrev.org/api/group#send_group_announcement)
 *
 * @author Forte Scarlet
 */
public class MilkySendGroupAnnouncementApi private constructor(
    private val param: MilkySendGroupAnnouncementParam
) : UnitResultMilkyApi(), MilkyGroupApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkySendGroupAnnouncementParam.serializer(), param)

    override fun toString(): String {
        return "MilkySendGroupAnnouncementApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "send_group_announcement"

        /**
         * 使用 [MilkySendGroupAnnouncementParam] 构建 [MilkySendGroupAnnouncementApi]。
         */
        @JvmStatic
        public fun create(param: MilkySendGroupAnnouncementParam): MilkySendGroupAnnouncementApi =
            MilkySendGroupAnnouncementApi(param)

        /**
         * 使用 API 入参字段构建 [MilkySendGroupAnnouncementApi]。
         * @param groupId 群号
         * @param content 公告内容
         * @param imageUri 公告附带图像文件 URI，支持 file://、http(s)://、base64:// 三种格式
         */
        @JvmStatic
        public fun create(groupId: Long, content: String, imageUri: String? = null): MilkySendGroupAnnouncementApi =
            create(MilkySendGroupAnnouncementParam(groupId, content, imageUri))
    }
}
