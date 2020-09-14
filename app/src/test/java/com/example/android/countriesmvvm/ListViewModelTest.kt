package com.example.android.countriesmvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.countriesmvvm.model.CountriesService
import com.example.android.countriesmvvm.model.Country
import com.example.android.countriesmvvm.viewmodel.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class ListViewModelTest {

    @Mock
    lateinit var countriesService: CountriesService

    @InjectMocks
    val listViewModel = ListViewModel()

    private var testSingle: Single<List<Country>>? = null

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    // the success path
    @Test // test annotation to know that this  will be run as a test
    fun getCountriesSuccess(){
        val country = Country("Country name","Capital","url")
        val countryList = arrayListOf(country)

        testSingle = Single.just(countryList)

        Mockito.`when`(countriesService.getCountries()).thenReturn((testSingle))

        listViewModel.refresh() // testing the functionality

        // check if all the value is correct
        Assert.assertEquals(1, listViewModel.countries.value?.size) // testing the size is one
        Assert.assertEquals(false,listViewModel.countryLoadError.value)
        Assert.assertEquals(false,listViewModel.loading.value)
    }

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun SetUpRxSchedulers(){
        val immediate = object : Scheduler() {
            // make sure delay is zero because we do not want any delay when calling schedulers
            override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler{
            Scheduler -> immediate
        }
        RxJavaPlugins.setInitComputationSchedulerHandler(){
                Scheduler -> immediate
        }
        RxJavaPlugins.setInitNewThreadSchedulerHandler(){
                Scheduler -> immediate
        }
        RxJavaPlugins.setInitSingleSchedulerHandler(){
                Scheduler -> immediate
        }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(){
                Scheduler -> immediate
        }




    }
}