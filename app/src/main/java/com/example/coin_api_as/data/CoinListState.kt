package com.example.coin_api_as.data

import com.example.coin_api_as.data.remote.dto.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val exchange: List<Coin> = emptyList(),
    val error: String = ""
)