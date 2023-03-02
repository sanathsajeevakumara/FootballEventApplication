package com.sanathcoding.footballeventapplication.mockserver

import com.sanathcoding.footballeventapplication.core.common.FootballValue
import com.sanathcoding.footballeventapplication.di.TestFootballModule
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [TestFootballModule::class]
)
class MockNetWorkModule: TestFootballModule() {
    override fun baseUrl(): String {
        return FootballValue.TEST_BASE_URL
    }
}