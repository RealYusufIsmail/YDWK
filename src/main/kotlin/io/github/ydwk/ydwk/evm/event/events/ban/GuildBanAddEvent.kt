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
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.ydwk.ydwk.evm.event.events.ban

import io.github.ydwk.yde.entities.Guild
import io.github.ydwk.yde.entities.guild.Member
import io.github.ydwk.ydwk.YDWK
import io.github.ydwk.ydwk.evm.annotations.GuildModerationEvent
import io.github.ydwk.ydwk.evm.event.Event

/**
 * This event is triggered when a member is banned from a guild.
 *
 * @param ydwk The [YDWK] instance.
 * @param guild The guild the member was banned from.
 * @param member The member that was banned.
 */
@GuildModerationEvent
data class GuildBanAddEvent(override val ydwk: YDWK, val guild: Guild, val member: Member?) :
    Event(ydwk)
