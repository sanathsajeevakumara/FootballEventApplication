package com.sanathcoding.footballeventapplication.di

import com.sanathcoding.footballeventapplication.core.common.FootballValue
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [FootballModule::class]
)
class FakeFootballModule: FootballModule() {
    override fun getBaseUrl(): String {
        return FootballValue.TEST_BASE_URL
    }
}