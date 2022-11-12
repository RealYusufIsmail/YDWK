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
package io.github.ydwk.ydwk.ws.voice.util

enum class VoiceCloseEventCode(private val code: Int, private val reason: String) {
    UNKNOWN_OPCODE(4001, "You sent an invalid opcode."),
    DECODE_ERROR(4002, "You sent an invalid payload in your identifying to the Gateway."),
    NOT_AUTHENTICATED(4003, "You sent a payload prior to identifying."),
    AUTHENTICATION_FAILED(4004, "The account token sent with your identify payload is incorrect."),
    ALREADY_AUTHENTICATED(4005, "More than one identify payload was sent."),
    SESSION_NO_LONGER_VALID(
        4006,
        "The session has been invalidated. You should reconnect and identify/resume accordingly."),
    SESSION_TIMEOUT(4009, "Your session timed out. Reconnect and re-identify."),
    SERVER_NOT_FOUND(4011, "The server you requested is not available."),
    UNKNOWN_PROTOCOL(4012, "You sent an invalid protocol for the voice gateway."),
    DISCONNECTED(
        4014,
        "Channel was deleted, you were kicked, voice server changed, or the main gateway session was dropped. Should not reconnect."),
    VOICE_SERVER_CRASHED(4015, "The server crashed. Our bad! Try resuming."),
    MISSED_HEARTBEAT(4997, "You missed too many heartbeats, reconnecting."),
    UNKNOWN_ENCRYPTION_MODE(4016, "You sent an invalid encoding for the voice gateway."),
    RESUMED(4999, "A request to resume the session was sent.");

    fun getCode(): Int {
        return code
    }

    fun getReason(): String {
        return reason
    }

    companion object {
        fun fromCode(code: Int): VoiceCloseEventCode {
            for (value in values()) {
                if (value.getCode() == code) {
                    return value
                }
            }
            return UNKNOWN_OPCODE
        }
    }
}
