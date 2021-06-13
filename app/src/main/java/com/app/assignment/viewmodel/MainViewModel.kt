package com.app.assignment.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.app.assignment.model.APIResponse
import com.app.assignment.repository.MainRepository
import com.app.assignment.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.google.gson.Gson


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    val cartContent: LiveData<String> = repository.getContent()
    fun isSocketConnected() = viewModelScope.launch {
        repository.onWeSocketStarted()
            .onStart {
                Log.e("onStart","onStart")
            }
            .catch { e->
                Log.e("catch",""+e.message)

            }.collect {

            }
    }
}