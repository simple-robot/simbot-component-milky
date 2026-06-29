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

@file:OptIn(ExperimentalSerializationApi::class)

package love.forte.simbot.milky.model.event.data.message.segment.incoming

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.modules.PolymorphicModuleBuilder
import love.forte.simbot.common.collection.ConcurrentQueue
import love.forte.simbot.common.collection.ExperimentalSimbotCollectionApi
import love.forte.simbot.common.collection.createConcurrentQueue
import love.forte.simbot.milky.model.event.data.message.segment.incoming.MilkyRawIncomingMessageSegmentSerializationProviderLoader.loadProviders
import love.forte.simbot.milky.model.event.data.message.segment.incoming.MilkyRawIncomingMessageSegmentSerializationProviderLoader.registerProvider

/**
 * 为 [love.forte.simbot.milky.model.event.data.message.segment.incoming.MilkyRawIncomingMessageSegment] 进行 service provider 支持的类型。
 *
 * 在 JVM 中，此类型可以作为 SPI 通过 `ServiceLoader` 寻找并加载；
 * 在其他平台中，需要通过 [MilkyRawIncomingMessageSegmentSerializationProviderLoader.registerProvider]
 * 手动注册真正的 provider 实例。
 *
 * Provider 主要用于为标准 Milky 协议之外的扩展消息段补充多态序列化器。
 * 标准 [love.forte.simbot.milky.model.event.data.message.segment.incoming.MilkyRawIncomingMessageSegment] 类型由模块内建的 KSP 生成逻辑提供默认解析能力。
 *
 * @see MilkyRawIncomingMessageSegmentSerializationProviderLoader
 */
public interface MilkyRawIncomingMessageSegmentSerializationProvider {
    /**
     * 向 [PolymorphicModuleBuilder] 中提供若干 [love.forte.simbot.milky.model.event.data.message.segment.incoming.MilkyRawIncomingMessageSegment] 的多态序列化器。
     */
    public fun provide(polymorphicBuilder: PolymorphicModuleBuilder<MilkyRawIncomingMessageSegment>)
}

/**
 * [MilkyRawIncomingMessageSegmentSerializationProvider] 的加载与注册入口。
 *
 * [loadProviders] 会合并显式注册的 provider 与平台可发现的外部 provider。
 * JVM 平台会额外从 SPI 中加载外部 provider；非 JVM 平台默认没有外部发现能力，
 * 因此需要通过 [registerProvider] 显式注册。
 */
@OptIn(ExperimentalSimbotCollectionApi::class)
public object MilkyRawIncomingMessageSegmentSerializationProviderLoader {
    private val explicitlyProviders: ConcurrentQueue<MilkyRawIncomingMessageSegmentSerializationProvider> =
        createConcurrentQueue()

    /**
     * 显式注册一个 [MilkyRawIncomingMessageSegmentSerializationProvider]。
     *
     * 已注册 provider 会参与之后 [loadProviders] 的结果。
     */
    public fun registerProvider(provider: MilkyRawIncomingMessageSegmentSerializationProvider) {
        explicitlyProviders.add(provider)
    }

    /**
     * 加载当前可用的全部 [MilkyRawIncomingMessageSegmentSerializationProvider]。
     *
     * 返回结果包含通过 [registerProvider] 显式注册的 provider，以及平台相关的外部 provider。
     */
    public fun loadProviders(): List<MilkyRawIncomingMessageSegmentSerializationProvider> {
        val externalProviders = loadExternalProviders()
        return explicitlyProviders.toList() + externalProviders
    }
}

/**
 * 加载平台可发现的外部 [MilkyRawIncomingMessageSegmentSerializationProvider]。
 */
internal expect fun loadExternalProviders(): List<MilkyRawIncomingMessageSegmentSerializationProvider>
