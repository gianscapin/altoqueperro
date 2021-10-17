package com.ort.altoqueperro.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
open class PetRequest(
    var pet: Pet, var state: String, var creationDate: Date,
    var resolvedDate: Date?, var coordinates: String, var requestCreator: User,
    var requestConsumer: User?
) : Parcelable {

    fun comparePetTo(other: PetRequest): Int {
        return pet.compareTo(other.pet)
    }

}