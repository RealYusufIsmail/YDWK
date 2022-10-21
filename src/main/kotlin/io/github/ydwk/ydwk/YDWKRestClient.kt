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
package io.github.ydwk.ydwk

import io.github.ydwk.ydwk.entities.User
import io.github.ydwk.ydwk.rest.RestApiClient
import java.util.concurrent.CompletableFuture

interface YDWKRestClient : YDWK {
    /**
     * Requests a user using its id.
     *
     * @param id The id of the user.
     * @return The [CompletableFuture] object.
     */
    fun requestUser(id: Long): CompletableFuture<User>

    /**
     * Requests a user using its id.
     *
     * @param id The id of the user.
     * @return The [CompletableFuture] object.
     */
    fun requestUser(id: String): CompletableFuture<User> = requestUser(id.toLong())

    /**
     * Gets the rest api client.
     *
     * @return The rest api client.
     */
    val restApiClient: RestApiClient
}
