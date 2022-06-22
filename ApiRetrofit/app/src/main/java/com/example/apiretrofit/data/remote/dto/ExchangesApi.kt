package com.example.apiretrofit.data.remote.dto

import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangesApi {
    @GET("/v1/exchanges")
    suspend fun getExchanges(): List<Exchanges>

    @GET("/v1/exchanges/{exchangeId}")
    suspend fun getExchanges(@Path("exchangeId") exchangeId: String): Exchanges
}