package com.example.coin_api_as.data.remote

import com.example.coin_api_as.data.remote.dto.Coin
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {
    @GET("/Coins")
    suspend fun getExchange(): List<Coin>
}