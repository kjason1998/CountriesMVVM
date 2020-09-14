package com.example.android.countriesmvvm.di


import com.example.android.countriesmvvm.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [CountriesServiceModule::class])
interface ServiceComponent {
    fun inject(viewModel: ListViewModel)
}