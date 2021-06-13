package com.app.assignment.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.app.assignment.model.APIResponse
import com.app.assignment.network.ApiService
import com.app.assignment.network.SocketListener
import com.app.assignment.utils.OnMultipleMessageReceivedListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class MainRepository @Inject constructor(private val apiService: ApiService) : OnMultipleMessageReceivedListener{

    private val contentRepository = MutableLiveData<String>()
    fun getContent() : LiveData<String> = contentRepository

    fun setContent(items: String) {
        GlobalScope.launch {
            contentRepository.postValue(items)
        }
    }
    fun setFailure(items: String) {
        GlobalScope.launch {
            contentRepository.postValue(items)
        }
    }

    val data: MutableLiveData<String> = MutableLiveData<String>()

    fun onWeSocketStarted() : Flow<WebSocket> = flow {
        emit(apiService.getInstance().newWebSocket(Request.Builder().url(
            apiService.BASE_URL
        ).build(), SocketListener(this@MainRepository)
        ))
    }.flowOn(Dispatchers.IO)

    override fun onMultipleAQIDataReceived(jsonString: String) {
        GlobalScope.launch {
            setContent(jsonString)
            data.postValue(jsonString)
        }
    }
    override fun onConnectionFailure() {
        GlobalScope.launch {
            setFailure("failure")
            data.postValue("failure")
        }
    }
}