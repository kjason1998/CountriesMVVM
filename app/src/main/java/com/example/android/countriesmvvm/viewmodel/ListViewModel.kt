package com.example.android.countriesmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.countriesmvvm.model.Country

class ListViewModel: ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>() // true for error false otherwise
    val loading = MutableLiveData<Boolean>() // if application loading the data from backend

    fun refresh(){
        fetchCountries()
    }

    /**
     * TODO: replace the mock data with real data
     */
    private fun fetchCountries() {
        val mockData = listOf(Country("United Kingdom"),
            Country("Afghanistan"),Country("Bahrain"),
            Country("China"),Country("Estonia"),
            Country("Finland"),Country("Germany"),
            Country("Iceland"),Country("Japan"))

        countryLoadError.value = false
        loading.value = false
        countries.value = mockData
    }
}