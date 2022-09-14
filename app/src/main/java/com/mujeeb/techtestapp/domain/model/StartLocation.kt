package com.mujeeb.techtestapp.domain.model

import com.google.gson.annotations.SerializedName


data class StartLocation(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null

)