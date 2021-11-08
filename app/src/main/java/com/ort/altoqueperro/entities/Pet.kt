package com.ort.altoqueperro.entities

import android.os.Parcelable
import com.ort.altoqueperro.activities.MainActivity.Companion.score
import kotlinx.parcelize.Parcelize

@Parcelize
class Pet(
    var name: String?,
    var type: String,
    var size: String,
    var sex: String,
    //var photoUrl: String,
    var nose: String,
    var furLength: String,
    var furColor: String,
    var eyes: String,
    var comments: String?,
    var lostDate: String
) : Parcelable {

    constructor() : this("", "", "", "", "", "","","","","")

    fun compareTo(other: Pet): Int {
        var currentScore = 0
        if (type == other.type) {
            if (size == other.size) currentScore += score.size
            if (sex == other.sex) currentScore += score.sex
            if (nose == other.nose) currentScore += score.nose
            if (furLength == other.furLength) currentScore += score.furLength
            if (furLength == other.furLength) currentScore += score.furLength
            if (furColor == other.furColor) currentScore += score.furColor
            if (eyes == other.eyes) currentScore += score.eyeColor
        }
        return currentScore
    }
}