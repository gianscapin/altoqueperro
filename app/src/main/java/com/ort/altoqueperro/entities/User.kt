package com.ort.altoqueperro.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User(var nombre:String,var email:String,var password:String):Parcelable {

    constructor() : this("","","")

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Usuario(nombre='$nombre', email='$email', password='$password')"
    }



}
