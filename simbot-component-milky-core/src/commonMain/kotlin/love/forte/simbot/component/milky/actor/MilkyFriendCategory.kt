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

import love.forte.simbot.common.id.ID
import love.forte.simbot.common.id.IntID.Companion.ID
import love.forte.simbot.definition.Category
import love.forte.simbot.milky.model.entity.friend.MilkyRawFriendCategoryEntity
import love.forte.simbot.suspendrunner.STP
import kotlin.coroutines.CoroutineContext

/**
 * Milky 好友分组
 * @author Forte Scarlet
 */
@SubclassOptInRequired(InternalForInheritanceMilkyActorApi::class)
public interface MilkyFriendCategory : MilkyEntityActor<MilkyRawFriendCategoryEntity>, Category {
    override val rawEntity: MilkyRawFriendCategoryEntity
    override val coroutineContext: CoroutineContext

    /**
     * 好友分组 ID
     *
     * @see MilkyRawFriendCategoryEntity.categoryId
     */
    override val id: ID
        get() = rawEntity.categoryId.ID

    /**
     * 好友分组名称
     *
     * @see MilkyRawFriendCategoryEntity.categoryName
     */
    @STP
    override suspend fun name(): String = rawEntity.categoryName
}
