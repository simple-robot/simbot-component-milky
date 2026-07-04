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

package love.forte.simbot.component.milky.actor

import love.forte.simbot.definition.Actor

/**
 * Milky 组件中的行为对象。
 *
 * 所有 [MilkyActor] 中的 [coroutineContext] 均不包含任何 [kotlinx.coroutines.Job]。
 *
 * @author Forte Scarlet
 */
@SubclassOptInRequired(InternalForInheritanceMilkyActorApi::class)
public interface MilkyActor : Actor

/**
 * Milky 组件中的行为对象，且存在一个代表其数据信息的 entity 原始对象。
 *
 * 所有 [MilkyActor] 中的 [coroutineContext] 均不包含任何 [kotlinx.coroutines.Job]。
 *
 * @author Forte Scarlet
 */
@SubclassOptInRequired(InternalForInheritanceMilkyActorApi::class)
public interface MilkyEntityActor<E : Any> : MilkyActor {
    /**
     * 该行为对象所代表的原始数据信息。
     */
    public val rawEntity: E
}
