package com.scolotin.logintestapp.repository

import com.google.gson.GsonBuilder
import com.scolotin.logintestapp.BASE_URL
import com.scolotin.logintestapp.model.ServerAuthResponseDTO
import com.scolotin.logintestapp.model.ServerPaymentsResponseDTO
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {

    private val serverApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(ServerAPI::class.java)

    fun authUser(username: String, password: String, callback: Callback<ServerAuthResponseDTO>) {
        serverApi.authUser(username.toRequestBody(), password.toRequestBody()).enqueue(callback)
    }

    fun getPayments(token: String, callback: Callback<ServerPaymentsResponseDTO>) {
        serverApi.getPayments(token).enqueue(callback)
    }
}