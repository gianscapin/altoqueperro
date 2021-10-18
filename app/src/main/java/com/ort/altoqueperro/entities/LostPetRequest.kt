package com.ort.altoqueperro.entities

import com.google.android.gms.maps.model.LatLng
import java.util.*

class LostPetRequest(
    pet: Pet,
    state: State,
    creationDate: Date,
    resolvedDate: Date?,
    coordinates: LatLng?,
    requestCreator: User,
    requestConsumer: User?,
    rescueCenter: RescueCenter?)
    : PetRequest(pet, state, creationDate, resolvedDate, coordinates, requestCreator, requestConsumer) {
}