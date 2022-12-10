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
package io.github.ydwk.ydwk.interaction.application.sub

import io.github.ydwk.ydwk.impl.interaction.message.ComponentImpl
import io.github.ydwk.ydwk.interaction.message.ActionRow
import io.github.ydwk.ydwk.rest.result.NoResult
import java.util.concurrent.CompletableFuture

interface Reply {

    /**
     * Sets the reply as an ephemeral message.
     *
     * @param ephemeral Whether the reply is ephemeral.
     * @return The [Reply] instance.
     */
    fun isEphemeral(isEphemeral: Boolean): Reply

    /**
     * Sets the reply as a 'text to speech' message.
     *
     * @param isTTS Whether the message should be 'text to speech'.
     * @return The [Reply] instance.
     */
    fun isTTS(isTTS: Boolean): Reply

    /**
     * Adds an [ActionRow] to the reply.
     *
     * @param actionRow The [ActionRow] to add.
     * @return The [Reply] instance.
     */
    fun addActionRow(actionRow: ComponentImpl.ComponentCreator): Reply

    /**
     * Triggers the reply.
     *
     * @return The [Void] instance.
     */
    fun reply(): NoResult {
        return replyWithFuture().get()
    }

    /**
     * Replies and return a [CompletableFuture] that will be completed when the reply is sent.
     *
     * @return The [CompletableFuture] instance.
     */
    fun replyWithFuture(): CompletableFuture<NoResult>
}
