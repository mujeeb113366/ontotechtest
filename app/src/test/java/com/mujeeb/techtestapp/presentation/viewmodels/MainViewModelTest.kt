package com.mujeeb.techtestapp.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mujeeb.techtestapp.common.NetworkUtils
import com.mujeeb.techtestapp.domain.model.Response
import com.mujeeb.techtestapp.domain.usecases.GetRemoteUserData
import com.mujeeb.techtestapp.presentation.ui.RequestState
import com.mujeeb.techtestapp.utils.TestCoroutineRule
import com.mujeeb.techtestapp.utils.TestUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var networkUtils: NetworkUtils

    @Mock
    private lateinit var getRemoteUserData: GetRemoteUserData

    @Mock
    lateinit var dataObserver: Observer<RequestState>

    private lateinit var mainViewModel: MainViewModel

    private lateinit var response: Response


    @Before
    fun setup() {
        mainViewModel = MainViewModel(networkUtils, getRemoteUserData)

    }

    @Test
    fun givenNetworkIsAvailable_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {

            response = TestUtils.generateResponse()

            doReturn(true)
                .`when`(networkUtils)
                .isOnline()

            doReturn(response)
                .`when`(getRemoteUserData)
                .invoke()

            mainViewModel.userDataObservable.observeForever(dataObserver)
            mainViewModel.getUserData()
            verify(getRemoteUserData).invoke()
            verify(dataObserver).onChanged(RequestState.Success(response))
            mainViewModel.userDataObservable.removeObserver(dataObserver)


        }

    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {

            val errorMessage = "Error Message"
            response = TestUtils.generateResponse()

            doReturn(true)
                .`when`(networkUtils)
                .isOnline()


            doThrow(RuntimeException(errorMessage))
                .`when`(getRemoteUserData)
                .invoke()

            mainViewModel.userDataObservable.observeForever(dataObserver)
            mainViewModel.getUserData()
            verify(getRemoteUserData).invoke()
            verify(dataObserver).onChanged(
                RequestState.Error(
                    RuntimeException(errorMessage).message
                )
            )
            mainViewModel.userDataObservable.removeObserver(dataObserver)

        }
    }
}