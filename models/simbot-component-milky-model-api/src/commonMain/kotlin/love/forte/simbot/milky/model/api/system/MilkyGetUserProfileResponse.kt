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

import kotlinx.serialization.Serializable
import love.forte.simbot.milky.model.api.MilkyApiModelConstructor
import love.forte.simbot.milky.model.api.MilkyApiResponseModel
import love.forte.simbot.milky.model.entity.MilkyRawUserSex

/**
 * [get_user_profile 获取用户个人信息](https://milky.ntqqrev.org/api/system#get_user_profile)
 * API 的响应体结构模型（输出参数）。
 */
@Serializable
public data class MilkyGetUserProfileResponse
@MilkyApiModelConstructor private constructor(
    /**
     * 昵称
     */
    val nickname: String,
    /**
     * QID
     */
    val qid: String,
    /**
     * 年龄
     */
    val age: Int,
    /**
     * 性别
     */
    val sex: MilkyRawUserSex,
    /**
     * 备注
     */
    val remark: String,
    /**
     * 个性签名
     */
    val bio: String,
    /**
     * QQ 等级
     */
    val level: Int,
    /**
     * 国家或地区
     */
    val country: String,
    /**
     * 城市
     */
    val city: String,
    /**
     * 学校
     */
    val school: String,
) : MilkyApiResponseModel
