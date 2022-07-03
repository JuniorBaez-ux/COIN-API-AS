package com.example.coin_api_as.data.remote

import com.example.coin_api_as.data.remote.dto.CoinDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CoinApi {
    @GET("/Coins")
    suspend fun getExchange(): List<CoinDto>

    @POST("/Coins")
    suspend fun makePost(
        @Body post : CoinDto
    ) : Response<CoinDto>
}