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

package love.forte.simbot.milky.model.api.system

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.api.MilkyApiModelConstructor
import love.forte.simbot.milky.model.api.MilkyApiResponseModel

/**
 * [get_impl_info 获取协议端信息](https://milky.ntqqrev.org/api/system#get_impl_info)
 * API 的响应体结构模型（输出参数）。
 */
@Serializable
public data class MilkyGetImplInfoResponse
@MilkyApiModelConstructor private constructor(
    /**
     * 协议端名称
     */
    @SerialName("impl_name")
    val implName: String,
    /**
     * 协议端版本
     */
    @SerialName("impl_version")
    val implVersion: String,
    /**
     * 协议端使用的 QQ 协议版本
     */
    @SerialName("qq_protocol_version")
    val qqProtocolVersion: String,
    /**
     * 协议端使用的 QQ 协议平台
     */
    @SerialName("qq_protocol_type")
    val qqProtocolType: MilkyQqProtocolType,
    /**
     * 协议端实现的 Milky 协议版本
     */
    @SerialName("milky_version")
    val milkyVersion: String,
) : MilkyApiResponseModel
