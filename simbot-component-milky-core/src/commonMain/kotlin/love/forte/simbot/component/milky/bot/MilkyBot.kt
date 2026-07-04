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

package love.forte.simbot.component.milky.bot

import love.forte.simbot.bot.Bot
import love.forte.simbot.bot.InheritanceBotApi
import love.forte.simbot.bot.InitializableBot
import love.forte.simbot.common.id.ID
import love.forte.simbot.common.id.LongID
import love.forte.simbot.component.milky.component.MilkyComponent
import love.forte.simbot.milky.api.MilkyApiExecutor

/**
 *
 * @author Forte Scarlet
 */
@OptIn(InheritanceBotApi::class)
@SubclassOptInRequired(InternalForInheritanceMilkyBotApi::class)
public interface MilkyBot : Bot, InitializableBot {
    /**
     * 当前 Bot 的 **唯一标识**。注册时指定，通常建议使用 bot 的 QQ 号。
     */
    override val id: ID

    /**
     * 在 [启动][start] 成功并获取到 bot 个人登录信息后初始化的登录 QQ 号信息。
     *
     * @throws IllegalStateException 如果在 [启动][start] 之前调用此属性。
     */
    public val uin: LongID

    /**
     * 在 [启动][start] 成功并获取到 bot 个人登录信息后初始化的登录 QQ 的昵称信息。
     *
     * @throws IllegalStateException 如果在 [启动][start] 之前调用此属性。
     */
    override val name: String

    /**
     * Milky 组件对象
     */
    override val component: MilkyComponent

    /**
     * Milky Bot 的配置信息内容。
     */
    override val configuration: MilkyBotConfiguration

    /**
     * Milky Bot 的 API 执行器。
     * 在 [init] 之后初始化。
     *
     * @throws IllegalStateException 如果在 [init] 之前调用此属性。
     */
    public val apiExecutor: MilkyApiExecutor
}
