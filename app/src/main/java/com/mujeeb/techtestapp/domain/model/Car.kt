package com.mujeeb.techtestapp.domain.model

import com.google.gson.annotations.SerializedName


data class Car(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("make") var make: String? = null,
    @SerializedName("model") var model: String? = null,
    @SerializedName("registration_number") var registrationNumber: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("real_world_range") var realWorldRange: Int? = null,
    @SerializedName("last_energy_level") var lastEnergyLevel: String? = null,
    @SerializedName("ev_access") var evAccess: ArrayList<String> = arrayListOf()

)