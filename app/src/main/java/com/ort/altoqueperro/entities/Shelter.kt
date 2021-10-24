package com.ort.altoqueperro.entities

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
class Shelter(
    var name: String,
    var phoneNumber: String,
    @get:Exclude
    var coordinates: LatLng?,
    var imageUrl: String,
    var hoster: String
) :
    Parcelable {

    constructor() : this("", "", null, "", "")
}

