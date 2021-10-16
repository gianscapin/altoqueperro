package com.ort.altoqueperro.entities

import java.util.*

class FoundPetRequest(pet: Pet,
                      state: String,
                      creationDate: Date,
                      resolvedDate: Date?,
                      coordinates: String,
                      requestCreator: User,
                      requestConsumer: User? )
    : PetRequest(pet, state, creationDate, resolvedDate, coordinates, requestCreator, requestConsumer) {
}