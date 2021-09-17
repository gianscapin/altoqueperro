package com.ort.altoqueperro.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Address(var streetName: String, var streetNumber: String) :Parcelable{

    fun getFormattedAddress() : String{
        return "$streetName $streetNumber"
    }
}