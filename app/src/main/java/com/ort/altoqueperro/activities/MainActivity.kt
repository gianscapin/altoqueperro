package com.ort.altoqueperro.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ort.altoqueperro.R

class MainActivity : AppCompatActivity() {
    companion object {
        var score = Score(20, 30, 5, 20, 20, 5)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

class Score(
    val sex: Int,
    val size: Int,
    val nose: Int,
    var furLength: Int,
    var furColor: Int,
    val eyeColor: Int) {
}