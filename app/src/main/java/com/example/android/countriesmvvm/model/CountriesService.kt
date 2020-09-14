package com.example.android.countriesmvvm.model

import com.example.android.countriesmvvm.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

/**
 * retrieve information from the backend
 */
class CountriesService {

    @Inject
    lateinit var api: CountriesAPI

    init {
        /**
         * DaggerApiComponent auto generate please do not forget to make sure when using kotlin
         * change the gradle from annotationProcessor to kapt for the dagger dependencies
         * add dont forget to add apply plugin: 'kotlin-kapt' on the top og build gradle app.
         * and press rebuild
         **/
        DaggerApiComponent.create().inject(this) // this -> countries service
    }

    /**
     * this is called in view model
     * Single means that observable only emits 1 value and then closes
     */
    fun getCountries(): Single<List<Country>> {
        return api.getCountries()
    }
}