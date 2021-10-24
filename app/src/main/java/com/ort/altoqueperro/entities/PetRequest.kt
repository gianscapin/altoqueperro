package com.ort.altoqueperro.entities

import android.net.wifi.rtt.CivicLocationKeys.STATE
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.GeoPoint
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.*

@Parcelize
open class PetRequest(
    var pet: Pet,
    var state: Int,
    var creationDate: Date,
    var resolvedDate: Date?,
    var coordinates: Coordinates?,
    var requestCreator: String,
    var requestConsumer: String?
) : Parcelable {

    constructor():this(Pet(), 1,Calendar.getInstance().time, null,null, "", null )

    fun comparePetTo(other: PetRequest): Int {
        return pet.compareTo(other.pet)
    }

}