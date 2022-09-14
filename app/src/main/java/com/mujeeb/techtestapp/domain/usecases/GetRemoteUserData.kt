package com.mujeeb.techtestapp.domain.usecases

import com.mujeeb.techtestapp.data.repo.RemoteDataSource
import com.mujeeb.techtestapp.domain.model.Response
import javax.inject.Inject

class GetRemoteUserData  @Inject constructor(private val remoteDataSource: RemoteDataSource)  {
    suspend operator fun invoke(): Response {
        return remoteDataSource.getUserData()
    }
}
