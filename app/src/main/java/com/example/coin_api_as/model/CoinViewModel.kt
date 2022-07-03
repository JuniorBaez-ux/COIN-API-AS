package com.example.coin_api_as.model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coin_api_as.data.CoinListState
import com.example.coin_api_as.data.remote.dto.CoinDto
import com.example.coin_api_as.repository.CoinRepository
import com.example.coin_api_as.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel() {
    var descripcion by mutableStateOf("")
    var valor by mutableStateOf("")

    private var _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    var myResponse: MutableLiveData<Response<CoinDto>> = MutableLiveData()

    fun obtainCoins(){
        coinRepository.getExchanges().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = CoinListState(exchange = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?: "Error desconocido")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun makePost(){
        viewModelScope.launch {
            coinRepository.makePost(CoinDto(null, descripcion = descripcion, valor = valor.toDouble(), ""))
        }
    }

    init {
        obtainCoins()
    }

}