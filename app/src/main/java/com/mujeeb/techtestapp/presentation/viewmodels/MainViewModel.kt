package com.mujeeb.techtestapp.presentation.viewmodels

import androidx.lifecycle.*
import com.mujeeb.techtestapp.common.NetworkUtils
import com.mujeeb.techtestapp.domain.usecases.GetRemoteUserData
import com.mujeeb.techtestapp.presentation.ui.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkUtils: NetworkUtils,
    private val getRemoteUserData: GetRemoteUserData
): ViewModel()   {


    val userDataObservable = MutableLiveData<RequestState>()

    fun getUserData(){
        viewModelScope.launch {
            try {

                if (networkUtils.isOnline()) {
                     val response = getRemoteUserData()
                     userDataObservable.value = RequestState.Success(response)
                }else{
                    userDataObservable.value = RequestState.NetworkError()
                }

            } catch (error: Throwable) {
                userDataObservable.value  = RequestState.Error(error.message)
            }
        }

    }



}