package com.ort.altoqueperro.entities

import android.media.Image
import android.os.Parcelable
import java.io.Serializable

class Vet (
    var id:String,
    var name:String,
    var latitude:Double,
    var longitude:Double,
    var neighborhood:String,
    var phone:String,
    var street:String,
    var streetNumber:String,
    var description: String?,
    var businessHours:String,
    var logoURL: String?,
    var city:String,
    var country:String
) : Serializable {
}