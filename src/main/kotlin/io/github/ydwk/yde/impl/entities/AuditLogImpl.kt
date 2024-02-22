/*
 * Copyright 2024 YDWK inc.
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
package io.github.ydwk.yde.impl.entities

import com.fasterxml.jackson.databind.JsonNode
import io.github.ydwk.yde.YDE
import io.github.ydwk.yde.entities.AuditLog
import io.github.ydwk.yde.entities.audit.AuditLogEntry
import io.github.ydwk.yde.interaction.application.type.SlashCommand

abstract class AuditLogImpl(
    override val yde: YDE,
    override val json: JsonNode,
    override val applicationCommands: List<SlashCommand>,
    override val entries: List<AuditLogEntry>
) : AuditLog
