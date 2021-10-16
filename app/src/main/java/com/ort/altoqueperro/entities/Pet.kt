package com.ort.altoqueperro.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Pet(var name: String, var type: String, var size: String, var sex: String, var coat: String, var eyeColor: String) : Parcelable {
    //ToDo cambiar el state a una clase? o usar los requests
}