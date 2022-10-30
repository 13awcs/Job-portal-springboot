package com.example.joblist.utils

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val isLoading: Boolean = false
) {
    class Success<T>(data: T?, message: String) : Resource<T>(data = data, message = message)
    class Error<T>(message: String) : Resource<T>(message = message)
    class Loading<T>(isLoading: Boolean) : Resource<T>(isLoading = isLoading)
}
