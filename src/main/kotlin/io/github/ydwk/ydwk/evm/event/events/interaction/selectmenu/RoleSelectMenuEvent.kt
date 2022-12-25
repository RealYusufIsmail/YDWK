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
package io.github.ydwk.ydwk.evm.event.events.interaction.selectmenu

import io.github.ydwk.ydwk.YDWK
import io.github.ydwk.ydwk.evm.event.Event
import io.github.ydwk.ydwk.interaction.message.selectmenu.types.RoleSelectMenu

/**
 * The event that is triggered when a user selects a role select menu.
 *
 * @param ydwk the [YDWK] instance
 * @param selectMenu the [RoleSelectMenu] that is selected
 */
data class RoleSelectMenuEvent(override val ydwk: YDWK, val selectMenu: RoleSelectMenu) :
    Event(ydwk)
