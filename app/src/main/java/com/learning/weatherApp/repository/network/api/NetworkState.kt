package com.learning.weatherApp.repository.network.api

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState(val status: Status, val msg: String) {
    companion object {
        @JvmStatic
        val LOADED: NetworkState
        @JvmStatic
        val LOADING: NetworkState
        @JvmStatic
        val ERROR: NetworkState

        init {
            LOADED = NetworkState(Status.SUCCESS, "Success")
            LOADING = NetworkState(Status.RUNNING, "Running")
            ERROR = NetworkState(Status.FAILED, "Something went wrong")
        }
    }
}