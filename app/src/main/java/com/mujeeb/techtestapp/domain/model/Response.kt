package com.mujeeb.techtestapp.domain.model

import com.google.gson.annotations.SerializedName


data class Response(

    @SerializedName("username") var username: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("profile") var profile: Profile? = Profile(),
    @SerializedName("bookings") var bookings: ArrayList<Bookings> = arrayListOf(),
    @SerializedName("userhours") var userhours: ArrayList<Userhours> = arrayListOf()

)