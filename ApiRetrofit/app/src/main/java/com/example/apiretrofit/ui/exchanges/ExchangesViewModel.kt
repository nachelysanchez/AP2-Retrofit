package com.example.apiretrofit.ui.exchanges

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiretrofit.data.remote.dto.ExchangeListState
import com.example.apiretrofit.data.repositorios.ExchangeRepository
import com.example.apiretrofit.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExchangesViewModel @Inject constructor(
    private val exchangeRepository: ExchangeRepository
) : ViewModel(){
    private var _state = mutableStateOf(ExchangeListState())
    val state: State<ExchangeListState> = _state

    init {
        exchangeRepository.getExchanges().onEach {
                result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = ExchangeListState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = ExchangeListState(exchanges = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ExchangeListState(error = result.message ?: "Error desconocido")
                }
            }
        }.launchIn(viewModelScope)
    }
}