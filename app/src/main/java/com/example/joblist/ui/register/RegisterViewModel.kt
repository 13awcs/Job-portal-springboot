package com.example.joblist.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joblist.api.AppRetrofit
import com.example.joblist.api.services.AppService
import com.example.joblist.entities.Register
import com.example.joblist.entities.User
import com.example.joblist.utils.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.awaitResponse

class RegisterViewModel : ViewModel() {
    private val appService: AppService by lazy {
        AppRetrofit.instance.create(AppService::class.java)
    }
    private lateinit var job: Job
    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            throwable.message?.let {
                viewModelScope.launch {
                    _registerState.emit(Resource.Error(throwable.toString()))
                    Log.e("TAG", "Error: $it")
                }
            }
        }
    private val _registerState = MutableStateFlow<Resource<User?>>(Resource.Loading(false))
    val registerState = _registerState.asStateFlow()

    fun register(email: String, username: String, password: String) {
        job = viewModelScope.launch(coroutineExceptionHandler) {
            _registerState.emit(Resource.Loading(true))

            withContext(Dispatchers.IO) {
                val response = appService.register(Register(email, username, password)).awaitResponse()
                _registerState.emit(
                    if (response.isSuccessful) Resource.Success(
                        response.body()?.data, response.body()!!.message
                    )
                    else Resource.Error(response.errorBody().toString())
                )
            }
        }.apply {
            invokeOnCompletion {
                viewModelScope.launch {
                    _registerState.emit(Resource.Loading(false))
                }
            }
        }
    }

    fun cancel() {
        if (job.isActive) job.cancel(CancellationException("Stop working"))
        viewModelScope.launch {
            _registerState.emit(Resource.Error("Cancelled"))
        }
    }
}
