package com.ort.altoqueperro.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Pet(var name: String, var state: String, var pictureUrl : String) : Parcelable {
    //ToDo cambiar el state a una clase? o usar los requests
}