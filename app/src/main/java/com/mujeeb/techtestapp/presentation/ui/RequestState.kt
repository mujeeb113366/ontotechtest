package com.mujeeb.techtestapp.presentation.ui

import com.mujeeb.techtestapp.domain.model.Response

sealed class RequestState {
    data class Success(val response : Response) : RequestState()
    object NetworkError : RequestState()
    data class Error(val error: String?): RequestState()
}
