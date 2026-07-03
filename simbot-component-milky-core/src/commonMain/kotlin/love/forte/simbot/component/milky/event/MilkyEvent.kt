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

package love.forte.simbot.component.milky.event

import love.forte.simbot.component.milky.bot.MilkyBot
import love.forte.simbot.event.BotEvent
import love.forte.simbot.event.Event

/**
 * 一个 Milky 的事件类型。
 *
 * @author Forte Scarlet
 */
@SubclassOptInRequired(InternalForInheritanceMilkyEventApi::class)
public interface MilkyEvent : Event

/**
 * Milky 组件中与 [MilkyBot] 相关的事件类型。
 *
 * @author Forte Scarlet
 */
@SubclassOptInRequired(InternalForInheritanceMilkyEventApi::class)
public interface MilkyBotEvent : MilkyEvent, BotEvent {
    override val bot: MilkyBot
}
