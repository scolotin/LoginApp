package com.scolotin.logintestapp.model

data class Authentication(val status: String, val token: String? = "", val errorMsg: String? = "")