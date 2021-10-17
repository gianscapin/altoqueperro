package com.ort.altoqueperro.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ort.altoqueperro.R

class MainActivity : AppCompatActivity() {
    companion object {
        var score = Score(20, 30, 45, 5)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

class Score(val size: Int, val sex: Int, val coat: Int, val eyeColor: Int) {
}