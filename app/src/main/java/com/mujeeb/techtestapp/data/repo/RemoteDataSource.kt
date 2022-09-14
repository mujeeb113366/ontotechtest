package com.mujeeb.techtestapp.data.repo

import com.mujeeb.techtestapp.data.net.ApiCall
import com.mujeeb.techtestapp.domain.model.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiCall: ApiCall)  {
    suspend fun getUserData(): Response{
        return apiCall.getUserData()
    }
}
