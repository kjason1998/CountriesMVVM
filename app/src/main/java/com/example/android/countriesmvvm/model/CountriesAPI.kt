package com.example.android.countriesmvvm.model

import io.reactivex.Single
import retrofit2.http.GET

/**
 * define the fucntion that will get called to retrive information from backend
 */
interface CountriesAPI {

    // after get is the endpoint
    @GET("DevTides/countries/master/countriesV2.json")
    fun getCountries(): Single<List<Country>>


}