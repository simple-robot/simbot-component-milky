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

package love.forte.simbot.component.milky.component

import kotlinx.serialization.modules.SerializersModule
import love.forte.simbot.common.function.ConfigurerFunction
import love.forte.simbot.common.function.invokeWith
import love.forte.simbot.common.services.Services
import love.forte.simbot.common.services.addProviderExceptJvm
import love.forte.simbot.component.*
import love.forte.simbot.component.milky.Milky

/**
 *
 * @author Forte Scarlet
 */
public class MilkyComponent : Component {
    override val id: String
        get() = ID_VALUE

    override val serializersModule: SerializersModule
        get() = Factory.serializersModule

    public companion object Factory : ComponentFactory<MilkyComponent, MilkyComponentConfiguration> {
        init {
            // Fix https://github.com/simple-robot/simpler-robot/pull/833
            Services.addProviderExceptJvm(ComponentFactoryProvider::class) {
                MilkyComponentFactoryProvider()
            }
        }

        public const val ID_VALUE: String = "simbot.milky"

        public val serializersModule: SerializersModule
            get() = Milky.serializersModule

        override val key: ComponentFactory.Key = object : ComponentFactory.Key {}

        override fun create(
            context: ComponentConfigureContext,
            configurer: ConfigurerFunction<MilkyComponentConfiguration>
        ): MilkyComponent {
            // 配置里面目前没东西，所以只执行一下配置逻辑就行了
            configurer.invokeWith(MilkyComponentConfiguration())
            return MilkyComponent()
        }
    }
}


/**
 * [MilkyComponent.Factory] 使用的配置类。
 */
public class MilkyComponentConfiguration

/**
 * 用于通过 SPI 自动加载 [MilkyComponent] 的 provider。
 */
public class MilkyComponentFactoryProvider : ComponentFactoryProvider<MilkyComponentConfiguration> {
    override fun loadConfigures(): Sequence<MilkyComponentFactoryConfigurerProvider> =
        loadMilkyComponentConfigures()

    override fun provide(): ComponentFactory<*, MilkyComponentConfiguration> = MilkyComponent
}

/**
 * 用于提供额外的 [MilkyComponentFactoryProvider] 配置器的 provider，
 * 会在 [MilkyComponentFactoryProvider.loadConfigures] 中被加载。
 */
public interface MilkyComponentFactoryConfigurerProvider :
    ComponentFactoryConfigurerProvider<MilkyComponentConfiguration>

internal expect fun loadMilkyComponentConfigures(): Sequence<MilkyComponentFactoryConfigurerProvider>
