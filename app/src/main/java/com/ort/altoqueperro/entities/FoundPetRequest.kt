package com.ort.altoqueperro.entities

import java.util.*


class FoundPetRequest(
    id: String,
    pet: Pet,
    state: Int,
    creationDate: Date,
    resolvedDate: Date?,
    coordinates: Coordinates?,
    requestCreator: String, // user o string?
    requestConsumer: String?,
    rescueCenter: String?
) : PetRequest(
    id,
    pet,
    state,
    creationDate,
    resolvedDate,
    coordinates,
    requestCreator,
    requestConsumer
) {

    constructor() : this(
        "",
        Pet(), State.OPEN.ordinal,
        Calendar.getInstance().time, null, null, "", null, null
    )

    constructor(
        pet: Pet,
        coordinates: Coordinates?,
        requestCreator: String
    ) : this(
        "",
        pet, State.OPEN.ordinal,
        Calendar.getInstance().time, null, coordinates, requestCreator, null, null
    )

}
