package com.example.android.countriesmvvm.model

import com.google.gson.annotations.SerializedName

data class Country(
    // this will map json name value to countryName
    @SerializedName("name")
    val countryName: String?,

    @SerializedName("capital")
    val countryCapital: String?,

    @SerializedName("flagPNG")
    val countryFlagUrl: String?
)