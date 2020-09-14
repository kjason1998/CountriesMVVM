package com.example.android.countriesmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.countriesmvvm.model.CountriesService
import com.example.android.countriesmvvm.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {

    private val countriesService = CountriesService() // to connect the service to get data
    private val disposable = CompositeDisposable() // close connection when this view model is close

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>() // true for error false otherwise
    val loading = MutableLiveData<Boolean>() // if application loading the data from backend

    fun refresh(){
        fetchCountries()
    }

    /**
     * get data from countries service
     */
    private fun fetchCountries() {
        loading.value = true
        disposable.add(countriesService.getCountries()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<List<Country>>(){
                override fun onSuccess(value: List<Country>?) {
                    countries.value = value
                    countryLoadError.value = false
                    loading.value = false
                }

                override fun onError(e: Throwable?) {
                    countryLoadError.value = true
                    loading.value = false
                }
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}