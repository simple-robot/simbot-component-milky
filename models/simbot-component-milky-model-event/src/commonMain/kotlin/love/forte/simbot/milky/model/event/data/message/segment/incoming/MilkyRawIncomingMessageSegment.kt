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
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import love.forte.simbot.milky.model.event.data.message.segment.incoming.MilkyRawIncomingMessageSegmentSerializerResolver.resolveMilkyRawIncomingMessageSegmentSerializer

/**
 * [IncomingSegment 接收消息段](https://milky.ntqqrev.org/struct/IncomingSegment).
 *
 * [MilkyRawIncomingMessageSegment] 是 `type + data` 形式的联合类型。
 */
public interface MilkyRawIncomingMessageSegment {
    public companion object {
        private val serializersModulesInstance by lazy {
            SerializersModule {
                polymorphic(MilkyRawIncomingMessageSegment::class) {
                    // load from Service Loader
                    for (provider in MilkyRawIncomingMessageSegmentSerializationProviderLoader.loadProviders()) {
                        provider.provide(this)
                    }

                    // standard default serializer
                    defaultDeserializer { className ->
                        if (className != null) {
                            resolveMilkyRawIncomingMessageSegmentSerializer(className)
                        } else {
                            // TODO Default deserializer
                            null
                        }
                    }
                }
            }
        }

        /**
         * 获取一个包含了 [MilkyRawIncomingMessageSegment] 的多态序列化器的 [SerializersModule]。
         */
        public fun serializersModules(): SerializersModule = serializersModulesInstance
    }

}
