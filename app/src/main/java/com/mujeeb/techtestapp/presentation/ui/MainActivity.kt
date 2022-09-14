package com.mujeeb.techtestapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.mujeeb.techtestapp.R
import com.mujeeb.techtestapp.domain.model.Response
import com.mujeeb.techtestapp.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.userDataObservable.observe(this, Observer {
            it?.let {
                when (it) {
                    is RequestState.Success -> {
                        showResponse(it.response)
                    }
                    is RequestState.NetworkError -> Toast.makeText(this,getString(R.string.network_unavailable_message), Toast.LENGTH_LONG).show()
                    is RequestState.Error -> Toast.makeText(this, it.error, Toast.LENGTH_LONG).show()

                }
            }
        })

        mainViewModel.getUserData()

    }

    private fun showResponse(response: Response) {
          Log.i("userData", response.toString())
    }
}