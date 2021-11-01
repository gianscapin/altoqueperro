package com.ort.altoqueperro.entities

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
class Vet (
    var name:String,
    var coordinates: Coordinates?,
    var address: String,
    //var neighborhood:String,
    var phone:String,
    var localidad: String,
    //var street:String,
    //var streetNumber:String,
    var description: String?,
    var businessHours:String,
    var imageUrl: String?,
    //var city:String,
    //var country:String
) : Parcelable {

    constructor():this("",null,"", "", "", "","","")

}