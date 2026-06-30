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
import love.forte.simbot.milky.model.api.group.MilkySetGroupAvatarParam
import kotlin.jvm.JvmStatic

/**
 * [set_group_avatar 设置群头像](https://milky.ntqqrev.org/api/group#set_group_avatar)
 *
 * @author Forte Scarlet
 */
public class MilkySetGroupAvatarApi private constructor(
    private val param: MilkySetGroupAvatarParam
) : UnitResultMilkyApi(), MilkyGroupApi {
    override val apiName: String
        get() = API_NAME

    override fun bodyContent(): String =
        MilkyApi.defaultJson.encodeToString(MilkySetGroupAvatarParam.serializer(), param)

    override fun toString(): String {
        return "MilkySetGroupAvatarApi(param=$param)"
    }

    public companion object {
        public const val API_NAME: String = "set_group_avatar"

        /**
         * 使用 [MilkySetGroupAvatarParam] 构建 [MilkySetGroupAvatarApi]。
         */
        @JvmStatic
        public fun create(param: MilkySetGroupAvatarParam): MilkySetGroupAvatarApi = MilkySetGroupAvatarApi(param)

        /**
         * 使用 API 入参字段构建 [MilkySetGroupAvatarApi]。
         * @param groupId 群号
         * @param imageUri 头像文件 URI，支持 file://、http(s)://、base64:// 三种格式
         */
        @JvmStatic
        public fun create(groupId: Long, imageUri: String): MilkySetGroupAvatarApi =
            create(MilkySetGroupAvatarParam(groupId, imageUri))
    }
}
