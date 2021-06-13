package com.app.assignment.utils

sealed class ApiState<out E>{

    // Any State in application are introduce here

    // Manage State such as Loading State,Various, Sucess state which we get from server
    // so sealed class is used here
    // work as abstract

    data class Success<out T>(val data: T) : ApiState<T>()
    data class Error(val exception: String) : ApiState<Nothing>()
    object Loading : ApiState<Nothing>()
    object Empty : ApiState<Nothing>()

}
