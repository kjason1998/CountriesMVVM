package com.example.android.countriesmvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.countriesmvvm.R
import com.example.android.countriesmvvm.model.Country
import com.example.android.countriesmvvm.util.getProgressDrawable
import com.example.android.countriesmvvm.util.loadImage
import kotlinx.android.synthetic.main.item_country.view.*

/**
 * TODO: add data binding
 * TODO: maybe remove parameter arraylist instead have it as a variable that can be access (public)
 */
class CountryListAdapter(var countriesList: ArrayList<Country>):
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    class CountryViewHolder(itemCountryView: View): RecyclerView.ViewHolder(itemCountryView){

        private val countryName = itemCountryView.countriesNameTextView
        private val countryCapital = itemCountryView.countriesCapitalNameTextView
        private val countryFlag = itemCountryView.flagImageView
        private val progressDrawable = getProgressDrawable(itemCountryView.context)
        // progressDrawable is a spinnr inside the spinner when it is being load up

        fun bind(country: Country){
            countryName.text = country.countryName
            countryCapital.text = country.countryCapital
            countryFlag.loadImage(country.countryFlagUrl, progressDrawable)
        }
    }

    /**
     * TODO: use DiffUtil
     */
    fun setCountriesData(newCountriesList: List<Country>) {
        countriesList.clear()
        countriesList.addAll(newCountriesList)
        // this will update everything that why it is better to use DiffUtil
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countriesList[position])
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

}