package com.scolotin.logintestapp.repository

import com.scolotin.logintestapp.APP_KEY
import com.scolotin.logintestapp.V
import com.scolotin.logintestapp.model.ServerAuthResponseDTO
import com.scolotin.logintestapp.model.ServerPaymentsResponseDTO
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ServerAPI {
    @Multipart
    @POST("login")
    @Headers("app-key:$APP_KEY", "v:$V")
    fun authUser(@Part("login") login: RequestBody, @Part("password") password: RequestBody) : Call<ServerAuthResponseDTO>

    @GET("payments")
    @Headers("app-key:$APP_KEY", "v:$V")
    fun getPayments(@Query("token") token: String) : Call<ServerPaymentsResponseDTO>
}