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
    var coat: String,
    var eyeColor: String
) : Parcelable {

    constructor() : this("", "", "", "", "", "")

    fun compareTo(other: Pet): Int {
        var currentScore = 0
        if (type == other.type) {
            if (size == other.size) currentScore += score.size
            if (sex == other.sex) currentScore += score.sex
            if (coat == other.coat) currentScore += score.coat
            if (eyeColor == other.eyeColor) currentScore += score.eyeColor
        }
        return currentScore
    }
}