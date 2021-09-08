package com.ort.altoqueperro.entities

import android.os.Parcel
import android.os.Parcelable

class User(nombre:String, email:String, password:String):Parcelable {
    var nombre: String

    var email: String

    var password: String

    constructor() : this("","","")

    init {
        this.nombre = nombre!!
        this.email = email!!
        this.password = password!!
    }

    constructor(source: Parcel) :this(
        source.readString()!!,
        source.readString()!!,
        source.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(email)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "Usuario(nombre='$nombre', email='$email', password='$password')"
    }



}
