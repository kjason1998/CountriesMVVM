package com.example.android.countriesmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.countriesmvvm.R
import com.example.android.countriesmvvm.viewmodel.ListViewModel
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf()) // passed empty arraylist

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        setRecyclerView()

        //handle refresh when pull down
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    private fun setRecyclerView() {
        /**
         * this is equivalent with
         * countriesRecyclerView.layoutManager = LinearLayoutManager(context)
         * countriesRecyclerView.adapter = countriesAdapter
         */
        countriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SlideInBottomAnimationAdapter(countriesAdapter)
        }
    }

    private fun observeViewModel() {
        // Observer data countries
        viewModel.countries.observe(this, Observer { countries ->
            //?. if countries is not null
            countries?.let{
                countriesAdapter.setCountriesData(it) // it here is countries if it is not null
                countriesRecyclerView.visibility = View.VISIBLE
                loadingRecyclerViewProgressBar.visibility = View.GONE
                errorRecyclerViewText.visibility = View.GONE
            }
        })

        viewModel.countryLoadError.observe(this, Observer { isErr ->
            isErr?.let {
                errorRecyclerViewText.visibility = if(it) View.VISIBLE else View.GONE
                if(it){
                    loadingRecyclerViewProgressBar.visibility = View.GONE
                    countriesRecyclerView.visibility = View.GONE
                }
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loadingRecyclerViewProgressBar.visibility = if(it) View.VISIBLE else View.GONE
                if(it){
                    errorRecyclerViewText.visibility = View.GONE
                    countriesRecyclerView.visibility = View.GONE
                }
            }
        })
    }

}