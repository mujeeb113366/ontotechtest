package com.mujeeb.techtestapp.data.net

import com.mujeeb.techtestapp.BuildConfig
import com.mujeeb.techtestapp.domain.model.Response
import retrofit2.http.GET

interface ApiCall {
    @GET(BuildConfig.USER_ENDPOINT)
    suspend fun getUserData(): Response
}