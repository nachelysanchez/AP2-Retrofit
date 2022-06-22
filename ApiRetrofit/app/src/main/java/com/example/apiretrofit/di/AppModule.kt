package com.example.apiretrofit.di

import com.example.apiretrofit.data.remote.dto.ExchangesApi
import com.example.apiretrofit.data.repositorios.ExchangeRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideExchangeApi(moshi: Moshi): ExchangesApi {
        return Retrofit.Builder()
            .baseUrl("https://api.coinpaprika.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ExchangesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideExchangeRepository(exchangeApi: ExchangesApi): ExchangeRepository {
        return ExchangeRepository(exchangeApi)
    }
}