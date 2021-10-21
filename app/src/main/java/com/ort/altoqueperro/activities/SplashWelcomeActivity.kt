package com.ort.altoqueperro.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.ort.altoqueperro.R

class SplashWelcomeActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 2500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_welcome)

        Handler().postDelayed(
            {
                startActivity(Intent(this,HomeNavigationActivity::class.java))
                finish()
            }
            , SPLASH_TIME_OUT)
    }
}