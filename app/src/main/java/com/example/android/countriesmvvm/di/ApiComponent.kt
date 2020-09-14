package com.example.android.countriesmvvm.di

import com.example.android.countriesmvvm.model.CountriesService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {


    fun inject(service: CountriesService)
}