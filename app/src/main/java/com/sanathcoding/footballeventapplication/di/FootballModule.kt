package com.sanathcoding.footballeventapplication.di

import android.app.Application
import com.sanathcoding.footballeventapplication.core.common.FootballValue.BASE_URL
import com.sanathcoding.footballeventapplication.data.remote.FootballApi
import com.sanathcoding.footballeventapplication.data.remote.repository.FootballRepositoryImpl
import com.sanathcoding.footballeventapplication.domain.repository.FootballRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class FootballModule {

    open fun getBaseUrl () = BASE_URL

    @Provides
    @Singleton
    fun provideFootballApi(okHttpClient: OkHttpClient): FootballApi {
        return Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(FootballApi::class.java)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Singleton
    @Provides
    fun providesFootBallRepository(api: FootballApi, application: Application): FootballRepository {
        return FootballRepositoryImpl(api, application)
    }

}