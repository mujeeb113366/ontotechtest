package com.mujeeb.techtestapp.domain.model

import com.google.gson.annotations.SerializedName


data class Bookings(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("start_location") var startLocation: StartLocation? = StartLocation(),
    @SerializedName("start_time") var startTime: String? = null,
    @SerializedName("car_class") var carClass: CarClass? = CarClass(),
    @SerializedName("car") var car: Car? = Car(),
    @SerializedName("real_start_time") var realStartTime: String? = null,
    @SerializedName("subscription_miles_left") var subscriptionMilesLeft: String? = null

)