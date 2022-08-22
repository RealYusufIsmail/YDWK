/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDWK contributors.
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
package io.github.realyusufismail.ws

import com.neovisionaries.ws.client.*
import io.github.realyusufismail.ydwk.YDWKInfo
import io.github.realyusufismail.ydwkimpl.YDWKImpl
import java.io.IOException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class WebSocketManager(
    private var ydwk: YDWKImpl,
    private var token: String,
    private var intent: Int
) : WebSocketAdapter(), WebSocketListener {
    private val logger: Logger = LoggerFactory.getLogger(javaClass) as Logger
    // Tha main websocket
    protected var webSocket: WebSocket? = null
    protected var resumeUrl: String? = null
    protected var sessionId: String? = null

    init {
        connect()
    }

    @Synchronized
    fun connect() {
        val url: String =
            (resumeUrl
                ?: YDWKInfo.DISCORD_GATEWAY_URL.toString()) +
                YDWKInfo.DISCORD_GATEWAY_VERSION.toString() +
                YDWKInfo.JSON_ENCODING.toString()

        try {
            val webSocketFactory = WebSocketFactory()
            when {
                webSocketFactory.socketTimeout > 0 ->
                    webSocketFactory.socketTimeout =
                        1000.coerceAtLeast(webSocketFactory.socketTimeout)
                else -> webSocketFactory.socketTimeout = 10000
            }

            webSocket =
                webSocketFactory
                    .createSocket(url)
                    .addHeader("Accept-Encoding", "gzip")
                    .addListener(this)
                    .connect()
        } catch (e: IOException) {
            resumeUrl = null
            throw RuntimeException(e)
        }
    }

    @Throws(Exception::class)
    override fun onConnected(websocket: WebSocket, headers: Map<String, List<String>>) {
        if (sessionId == null) {
            logger.info("Connected to gateway")
        } else {
            logger.info("Resuming session$sessionId")
        }

        if (sessionId == null) {
            ConnectSystem(ydwk, token, intent).identify()
        } else {
            ConnectSystem(ydwk, token, intent).resume()
        }
    }

    @Throws(Exception::class)
    override fun onConnectError(websocket: WebSocket, cause: WebSocketException) {
        logger.error("Error connecting to websocket", cause)
    }

    override fun onTextMessage(websocket: WebSocket, text: String) {}

    @Throws(Exception::class)
    override fun onDisconnected(
        websocket: WebSocket,
        serverCloseFrame: WebSocketFrame?,
        clientCloseFrame: WebSocketFrame?,
        closedByServer: Boolean
    ) {}

    override fun onError(websocket: WebSocket, cause: WebSocketException) {}
}
