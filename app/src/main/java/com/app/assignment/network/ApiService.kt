package com.app.assignment.network

import com.app.assignment.model.APIResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.cio.*
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.WebSocket
import javax.inject.Inject

public class ApiService @Inject constructor() {

    public val BASE_URL = "ws://city-ws.herokuapp.com/"
    val SOCKET_CLOSE = 1000


    private var networkClient: OkHttpClient? = null

    fun getInstance(): OkHttpClient {
        return networkClient ?: synchronized(this) {
            val instance = OkHttpClient()
            networkClient = instance
            instance
        }
    }




}
