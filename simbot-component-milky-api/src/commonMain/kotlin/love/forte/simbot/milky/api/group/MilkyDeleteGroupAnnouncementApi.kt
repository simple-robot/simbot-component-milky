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
import love.forte.simbot.milky.model.api.group.MilkyDeleteGroupAnnouncementParam
import kotlin.jvm.JvmStatic

/**
 * [delete_group_announcement 删除群公告](https://milky.ntqqrev.org/api/group#delete_group_announcement)
 *
 * @author Forte Scarlet
 */
public class MilkyDeleteGroupAnnouncementApi private constructor(
    private val param: MilkyDeleteGroupAnnouncementParam
) : UnitResultMilkyApi(), MilkyGroupApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkyDeleteGroupAnnouncementParam.serializer(), param)

    override fun toString(): String {
        return "MilkyDeleteGroupAnnouncementApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "delete_group_announcement"

        /**
         * 使用 [MilkyDeleteGroupAnnouncementParam] 构建 [MilkyDeleteGroupAnnouncementApi]。
         */
        @JvmStatic
        public fun create(param: MilkyDeleteGroupAnnouncementParam): MilkyDeleteGroupAnnouncementApi =
            MilkyDeleteGroupAnnouncementApi(param)

        /**
         * 使用 API 入参字段构建 [MilkyDeleteGroupAnnouncementApi]。
         * @param groupId 群号
         * @param announcementId 公告 ID
         */
        @JvmStatic
        public fun create(groupId: Long, announcementId: String): MilkyDeleteGroupAnnouncementApi =
            create(MilkyDeleteGroupAnnouncementParam(groupId, announcementId))
    }
}
