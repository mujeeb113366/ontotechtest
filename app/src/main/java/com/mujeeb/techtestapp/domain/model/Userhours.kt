package com.mujeeb.techtestapp.domain.model

import com.google.gson.annotations.SerializedName


data class Userhours(

    @SerializedName("car_class") var carClass: Int? = null,
    @SerializedName("hours_available") var hoursAvailable: String? = null

)