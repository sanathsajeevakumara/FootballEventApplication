package com.sanathcoding.footballeventapplication.di

import com.sanathcoding.footballeventapplication.data.remote.repository.FootballRepositoryImpl
import com.sanathcoding.footballeventapplication.domain.repository.FootballRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FootballRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFootballRepository(
        footballRepositoryImpl: FootballRepositoryImpl
    ): FootballRepository
}