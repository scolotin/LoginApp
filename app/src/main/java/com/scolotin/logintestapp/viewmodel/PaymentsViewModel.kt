package com.scolotin.logintestapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scolotin.logintestapp.model.*
import com.scolotin.logintestapp.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentsViewModel : ViewModel() {

    val payments: MutableLiveData<ArrayList<Payment>> = MutableLiveData()

    private val repository = Repository()

    private val callback = object : Callback<ServerPaymentsResponseDTO> {

        override fun onResponse(call: Call<ServerPaymentsResponseDTO>, response: Response<ServerPaymentsResponseDTO>) {
            val serverResponse: ServerPaymentsResponseDTO? = response.body()
            serverResponse?.response.let {
                payments.postValue(it)
            }
        }

        override fun onFailure(call: Call<ServerPaymentsResponseDTO>, t: Throwable) {

        }
    }

    fun getPayments(token: String) {
        repository.getPayments(token, callback)
    }
}
