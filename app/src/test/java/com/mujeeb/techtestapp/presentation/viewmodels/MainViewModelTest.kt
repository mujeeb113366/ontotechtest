package com.mujeeb.techtestapp.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mujeeb.techtestapp.common.NetworkUtils
import com.mujeeb.techtestapp.domain.model.Response
import com.mujeeb.techtestapp.domain.usecases.GetRemoteUserData
import com.mujeeb.techtestapp.presentation.ui.RequestState
import com.mujeeb.techtestapp.utils.TestUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
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

    /*
     *  A JUnit Test Rule that swaps the background executor used by the Architecture Components
     * with a different one which executes each task synchronously.
     */
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var networkUtils: NetworkUtils

    @Mock
    private lateinit var getRemoteUserData: GetRemoteUserData

    @Mock
    lateinit var dataObserver: Observer<RequestState>

    private lateinit var mainViewModel: MainViewModel

    private lateinit var response: Response

    /*
    * Enable us to run Unit test in the Dispatcher.Main thread by
    * replacing the Dispatcher with a UnconfinedTestDispatcher
    * */
    @ExperimentalCoroutinesApi
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        //Given dispatcher under the hood
        Dispatchers.setMain(testDispatcher)
        mainViewModel = MainViewModel(networkUtils, getRemoteUserData)

    }

    @After
    fun tearDown() {
        //Resets the state of theDispatchers.Main to the original main dispatcher
        Dispatchers.resetMain()

    }
    @Test
    fun givenNetworkIsAvailable_whenFetch_shouldReturnSuccess() {

        /*
         *  runBlocking()function can’t skip the delay peration, so runTest is used instead
         *  A suspend function can only be called from another suspend function or a Coroutine
         */
        runTest {


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
        runTest {

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