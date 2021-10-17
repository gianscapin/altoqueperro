package com.ort.altoqueperro.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PetScore(val request: PetRequest?, val score: Int) : Parcelable{

    /*fun getPet(): Pet{
        return request!!.pet
    }*/
}