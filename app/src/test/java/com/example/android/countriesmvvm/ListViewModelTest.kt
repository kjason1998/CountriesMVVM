package com.example.android.countriesmvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Rule
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class ListViewModelTest {

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