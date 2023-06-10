package com.hmwn.plaosanvr.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.utils.ViewState
import com.hmwn.plaosanvr.databinding.ActivitySplashBinding
import com.hmwn.plaosanvr.view.main.MainActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        startObserveData()

    }

    private fun initViewModel() {
        viewModel = SplashViewModel()
    }

    private fun startObserveData() {

        viewModel.progressState.observe(this) {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java)) // direct from splash to main activity
        }

    }

}