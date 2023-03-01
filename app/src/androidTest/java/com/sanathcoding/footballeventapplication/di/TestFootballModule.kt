package com.sanathcoding.footballeventapplication.di

import com.sanathcoding.footballeventapplication.core.common.FootballValue.BASE_URL
import com.sanathcoding.footballeventapplication.data.remote.FootballApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestFootballModule {

    @Provides
    @Singleton
    fun provideFootballApi(): FootballApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FootballApi::class.java)
    }

}