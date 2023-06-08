package com.hmwn.plaosanvr.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hmwn.plaosanvr.view.main.MainActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

//    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivitySplashBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        startActivity(Intent(this@SplashActivity, MainActivity::class.java)) // direct from splash to main activity

    }
}