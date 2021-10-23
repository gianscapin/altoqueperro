package com.ort.altoqueperro.entities

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
open class PetRequest(
    var pet: Pet,
    var state: State,
    var creationDate: Date,
    var resolvedDate: Date?,
    var coordinates: LatLng?,
    var requestCreator: User,
    var requestConsumer: User?
) : Parcelable {

    fun comparePetTo(other: PetRequest): Int {
        return pet.compareTo(other.pet)
    }

}