package com.scolotin.logintestapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scolotin.logintestapp.model.*
import com.scolotin.logintestapp.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    val authenticationStatus: MutableLiveData<Authentication> = MutableLiveData()

    private val repository = Repository()

    private val callback = object : Callback<ServerAuthResponseDTO> {

        override fun onResponse(call: Call<ServerAuthResponseDTO>, response: Response<ServerAuthResponseDTO>) {
            val serverResponse: ServerAuthResponseDTO? = response.body()
            serverResponse?.let {
                authenticationStatus.postValue(Authentication(it.success, it.response?.token, it.error?.error_msg))
            }
        }

        override fun onFailure(call: Call<ServerAuthResponseDTO>, t: Throwable) {
            authenticationStatus.postValue(Authentication("false", errorMsg = t.message))
        }
    }

    fun authUser(username: String, password: String) = authenticationStatus.apply {
        repository.authUser(username, password, callback)
    }
}