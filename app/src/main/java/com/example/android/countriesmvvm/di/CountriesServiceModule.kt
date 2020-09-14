package com.example.android.countriesmvvm.di

import com.example.android.countriesmvvm.model.CountriesService
import dagger.Module
import dagger.Provides

/**
 * dependency injection
 * this module will provide the countries api
 */
@Module
class CountriesServiceModule {

    @Provides
    fun provideCountriesService(): CountriesService {
        // build the service that we are going to use
        return CountriesService()
    }
}