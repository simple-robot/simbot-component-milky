/*
 *     Copyright (c) 2026. ForteScarlet.
 *
 *     Project    https://github.com/simple-robot/simpler-robot
 *     Email      ForteScarlet@163.com
 *
 *     This file is part of the Simple Robot Library (Alias: simple-robot, simbot, etc.).
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
 *     You should have received a copy of the Lesser GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package love.forte.simbot.milky.model.api.system

import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.api.MilkyApiModelConstructor

/**
 * [set_avatar 设置 QQ 账号头像](https://milky.ntqqrev.org/api/system#set_avatar)
 * 的请求入参。
 * @author Forte Scarlet
 */
@Serializable
public data class MilkySetAvatarParam
@MilkyApiModelConstructor constructor(
    /**
     * 头像文件 URI，支持 `file://` `http(s)://` `base64://` 三种格式。
     */
    val uri: String
)
