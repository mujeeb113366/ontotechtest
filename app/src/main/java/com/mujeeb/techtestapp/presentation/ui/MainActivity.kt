package com.mujeeb.techtestapp.presentation.ui

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.mujeeb.techtestapp.R
import com.mujeeb.techtestapp.databinding.ActivityMainBinding
import com.mujeeb.techtestapp.domain.model.Response
import com.mujeeb.techtestapp.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainViewModel.userDataObservable.observe(this, Observer {
            it?.let {
                when (it) {
                    is RequestState.Success -> {
                        showSubscriptionMilesLeft(it.response.bookings[0].subscriptionMilesLeft)
                    }
                    is RequestState.NetworkError -> Toast.makeText(this,getString(R.string.network_unavailable_message), Toast.LENGTH_LONG).show()
                    is RequestState.Error -> Toast.makeText(this, it.error, Toast.LENGTH_LONG).show()

                }
            }
        })


        mainViewModel.getUserData()

    }

    private fun showSubscriptionMilesLeft(subscriptionMilesLeft: String?) {
        val animator = ValueAnimator.ofInt(0, subscriptionMilesLeft?.toInt() ?: 0)
        animator.duration = DURATION
        animator.addUpdateListener { animation ->
            val progress = animation?.animatedValue as Int
            binding.pbMileageLevel.setProgress(progress)
            binding.tvMileageLevel.setText(progress.toString())
        }
        animator.start()

    }

    companion object{
        const val DURATION = 500L
    }

}