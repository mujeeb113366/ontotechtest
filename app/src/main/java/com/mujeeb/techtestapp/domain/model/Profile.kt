package com.mujeeb.techtestapp.domain.model

import com.google.gson.annotations.SerializedName


data class Profile(

    @SerializedName("first_name") var firstName: String? = null,
    @SerializedName("last_name") var lastName: String? = null,
    @SerializedName("hours_available") var hoursAvailable: String? = null

)