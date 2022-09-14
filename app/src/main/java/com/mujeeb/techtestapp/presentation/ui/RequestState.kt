package com.mujeeb.techtestapp.presentation.ui

import com.mujeeb.techtestapp.domain.model.Response

sealed class RequestState {
    class Success(val response : Response) : RequestState()
    class NetworkError(): RequestState()
    class Error(val error: String?): RequestState()
}
