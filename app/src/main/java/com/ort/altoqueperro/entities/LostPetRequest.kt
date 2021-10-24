package com.ort.altoqueperro.entities

import com.google.android.gms.maps.model.LatLng
import java.util.*

class LostPetRequest(
    pet: Pet,
    state: Int,
    creationDate: Date,
    resolvedDate: Date?,
    coordinates: LatLng?,
    requestCreator: String,
    requestConsumer: String?)
    : PetRequest(pet, state, creationDate, resolvedDate, coordinates, requestCreator, requestConsumer) {

    constructor() : this(Pet(), 1,
        Calendar.getInstance().time, null, null, "",null)
}