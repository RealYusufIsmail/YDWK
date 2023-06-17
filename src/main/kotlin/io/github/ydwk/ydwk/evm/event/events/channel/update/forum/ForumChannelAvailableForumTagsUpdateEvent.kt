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
package io.github.ydwk.ydwk.evm.event.events.channel.update.forum

import io.github.ydwk.yde.entities.channel.guild.forum.ForumTag
import io.github.ydwk.yde.entities.channel.guild.forum.GuildForumChannel
import io.github.ydwk.ydwk.YDWK
import io.github.ydwk.ydwk.evm.annotations.ChannelEvent
import io.github.ydwk.ydwk.evm.event.events.channel.GenericChannelUpdateEvent

/**
 * This event is triggered when a guild forum channel's available forum tags are updated.
 *
 * @param ydwk The [YDWK] instance.
 * @param entity The [GuildForumChannel] that was updated.
 * @param oldAvailableForumTags The old available forum tags.
 * @param newAvailableForumTags The new available forum tags.
 */
@ChannelEvent
data class ForumChannelAvailableForumTagsUpdateEvent(
    override val ydwk: YDWK,
    override val entity: GuildForumChannel,
    val oldAvailableForumTags: List<ForumTag>,
    val newAvailableForumTags: List<ForumTag>,
) :
    GenericChannelUpdateEvent<List<ForumTag>>(
        ydwk, entity, oldAvailableForumTags, newAvailableForumTags)
