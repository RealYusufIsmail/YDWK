/*
 * Copyright 2022 YDWK inc.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.ydwk.ydwk.impl.entities.channel.guild

import com.fasterxml.jackson.databind.JsonNode
import io.github.ydwk.ydwk.YDWK
import io.github.ydwk.ydwk.YDWKWebSocket
import io.github.ydwk.ydwk.entities.Guild
import io.github.ydwk.ydwk.entities.channel.guild.Category
import io.github.ydwk.ydwk.entities.channel.guild.vc.GuildVoiceChannel
import io.github.ydwk.ydwk.impl.entities.channel.VoiceChannelImpl

class GuildVoiceChannelImpl(
    override val ydwk: YDWK,
    override val json: JsonNode,
    override val idAsLong: Long
) : GuildVoiceChannel, VoiceChannelImpl(ydwk, json, idAsLong) {
    override val bitrate: Int
        get() = json["bitrate"].asInt()

    override val userLimit: Int
        get() = json["user_limit"].asInt()

    override val rateLimitPerUser: Int
        get() = json["rate_limit_per_user"].asInt()

    override val position: Int
        get() = json["position"].asInt()

    override val parent: Category?
        get() =
            if (ydwk is YDWKWebSocket) ydwk.getCategory(json["parent_id"].asText())
            else TODO("Add support for rest")

    override val guild: Guild
        get() =
            if (ydwk is YDWKWebSocket) {
                if (ydwk.getGuild(json["guild_id"].asText()) != null)
                    ydwk.getGuild(json["guild_id"].asText())!!
                else throw IllegalStateException("Guild is null")
            } else TODO("Add support for rest")

    override var name: String
        get() = json["name"].asText()
        set(value) {}
}
