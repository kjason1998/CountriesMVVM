package com.example.android.countriesmvvm.di

import com.example.android.countriesmvvm.model.CountriesAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * dependency injection
 * this module will provide the countries api
 */
@Module
class ApiModule {

    // this is like www.github.com and we use CountriesAPI to complete the request like kjason1998/RecordIt to get my github RecordIt repo
    private val BASE_URL = "https://raw.githubusercontent.com"

    @Provides
    fun provideCountriesAPI():CountriesAPI{
         // build the service that we are going to use
        return Retrofit.Builder() // create frame work for retrofit help get info from backend
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // we use gson converter becuase we gonna get gson and we want to convert it to Country.kt
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // it is RxJava adapter transform data (country.kt) into an observable variable
            .build().create(CountriesAPI::class.java)
    }
}