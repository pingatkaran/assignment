package com.app.assignment.utils

interface OnMultipleMessageReceivedListener {
        fun onMultipleAQIDataReceived(jsonString: String)
        fun onConnectionFailure()
    }