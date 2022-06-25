package com.example.coin_api_as.data.remote

import com.example.coin_api_as.data.remote.dto.CoinDto
import retrofit2.http.GET

interface CoinApi {
    @GET("/Coins")
    suspend fun getExchange(): List<CoinDto>
}