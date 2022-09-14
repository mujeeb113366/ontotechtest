package com.mujeeb.techtestapp.domain.model

import com.google.gson.annotations.SerializedName


data class CarClass(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("label") var label: String? = null

)