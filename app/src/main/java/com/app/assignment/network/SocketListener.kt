package com.app.assignment.network

import android.util.Log
import com.app.assignment.utils.OnMultipleMessageReceivedListener
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class SocketListener(private val onMultipleMessageReceivedListener: OnMultipleMessageReceivedListener) : WebSocketListener() {

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        Log.e("TAG", "onClosed: "+code+" - reason -"+reason )
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        Log.e("TAG", "onClosing: "+code+" - reason -"+reason )
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        Log.e("TAG", "onFailure: "+t.message+" - reason -"+response )
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        Log.e("TAG", "onMessage: "+text )
        onMultipleMessageReceivedListener.onMultipleAQIDataReceived(text)

    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)
        Log.e("TAG", "onMessage:bytes "+bytes )
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        Log.e("TAG", "onOpen: "+response )
    }
}