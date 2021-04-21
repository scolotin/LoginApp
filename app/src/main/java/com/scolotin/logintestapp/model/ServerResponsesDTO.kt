package com.scolotin.logintestapp.model

/* Data classes for Auth */
data class ServerAuthToken(val token: String)

data class ServerAuthError(val error_code: Int, val error_msg: String)

data class ServerAuthResponseDTO(val success: String, val response: ServerAuthToken?, val error: ServerAuthError?)

/* Data classes for Payments */
data class ServerPaymentsResponseDTO(val success: String, val response: ArrayList<Payment>)