package com.example.apiretrofit.data.repositorios

import com.example.apiretrofit.data.remote.dto.Exchanges
import com.example.apiretrofit.data.remote.dto.ExchangesApi
import com.example.apiretrofit.utils.Resource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ExchangeRepository @Inject constructor(
    private val api: ExchangesApi
){

    fun getExchanges(): Flow<Resource<List<Exchanges>>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val exchange = api.getExchanges() //descarga las monedas de internet, se supone quedemora algo

            emit(Resource.Success(exchange)) //indicar que se cargo correctamente y pasarle las monedas
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}