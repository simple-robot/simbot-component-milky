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

package love.forte.simbot.milky.api

import kotlinx.serialization.KSerializer
import love.forte.simbot.milky.model.api.MilkyApiResult

/**
 *
 * @author Forte Scarlet
 */
public abstract class SerializationBasedTypedMilkyApi<R : Any> : TypedMilkyApi<R> {
    protected abstract val contentSerializer: KSerializer<R>
    protected abstract val resultSerializer: KSerializer<MilkyApiResult<R>>


    override fun decodeResult(resultJson: String): MilkyApiResult<R> {
        return MilkyApi.defaultJson.decodeFromString(resultSerializer, resultJson)
    }

    override fun decodeResultContent(resultContentJson: String): R {
        return MilkyApi.defaultJson.decodeFromString(contentSerializer, resultContentJson)
    }
}
