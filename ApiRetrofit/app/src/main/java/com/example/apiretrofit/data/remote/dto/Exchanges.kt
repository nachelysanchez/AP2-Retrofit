package com.example.apiretrofit.data.remote.dto

data class Exchanges(
    val exchangeId : String = "",
    val name : String = "",
    val description : String? = "",
    val active : Boolean = false,
    val last_updated : String = ""
)