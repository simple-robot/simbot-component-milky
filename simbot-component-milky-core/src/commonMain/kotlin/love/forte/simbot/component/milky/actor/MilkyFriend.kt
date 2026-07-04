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

import love.forte.simbot.ability.DeleteOption
import love.forte.simbot.ability.DeleteSupport
import love.forte.simbot.common.id.LongID
import love.forte.simbot.common.id.LongID.Companion.ID
import love.forte.simbot.definition.Contact
import love.forte.simbot.milky.common.utils.InternalMilkyUtilApi
import love.forte.simbot.milky.common.utils.qqAvatar640
import love.forte.simbot.milky.model.entity.MilkyRawUserSex
import love.forte.simbot.milky.model.entity.friend.MilkyRawFriendEntity
import love.forte.simbot.suspendrunner.ST
import kotlin.coroutines.CoroutineContext

/**
 * 一个好友。
 * @author Forte Scarlet
 */
@SubclassOptInRequired(InternalForInheritanceMilkyActorApi::class)
public interface MilkyFriend : MilkyEntityActor<MilkyRawFriendEntity>, Contact, DeleteSupport {
    override val rawEntity: MilkyRawFriendEntity
    override val coroutineContext: CoroutineContext

    /**
     * 好友的 QQ 号
     */
    override val id: LongID
        get() = rawEntity.userId.ID

    override val name: String
        get() = rawEntity.nickname

    @OptIn(InternalMilkyUtilApi::class)
    override val avatar: String?
        get() = qqAvatar640(rawEntity.userId.toString())

    /**
     * 好友分组
     */
    public val category: MilkyFriendCategory

    /**
     * 性别
     */
    public val sex: MilkyRawUserSex
        get() = rawEntity.sex

    /**
     * QID
     */
    public val qid: String
        get() = rawEntity.qid

    /**
     * 备注
     */
    public val remark: String
        get() = rawEntity.remark

    /**
     * 发送好友戳一戳
     */
    @ST
    public suspend fun sendNudge()

    /**
     * 发送好友点赞
     *
     * @param count 发送次数。默认为1，最小为1。
     */
    @ST
    public suspend fun sendProfileLike(count: Int = 1)

    /**
     * 删除好友
     */
    @ST
    override suspend fun delete(vararg options: DeleteOption)


}
