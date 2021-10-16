package com.ort.altoqueperro.entities

import java.util.*

class LostPetRequest(pet: Pet,
                      state: String,
                      creationDate: Date,
                      resolvedDate: Date?,
                      coordinates: String,
                      requestCreator: User,
                      requestConsumer: User?,
                      pictureUrl: String,
                      rescueCenter: RescueCenter?)
    : PetRequest(pet, state, creationDate, resolvedDate, coordinates, requestCreator, requestConsumer) {
}