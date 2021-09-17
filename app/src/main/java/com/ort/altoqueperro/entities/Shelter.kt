package com.ort.altoqueperro.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Shelter(var name: String, var phoneNumber : String, var address: Address, var logoUrl: String, var description: String)  :
    Parcelable {

}

