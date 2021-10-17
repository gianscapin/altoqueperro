package com.ort.altoqueperro.entities

import com.google.android.gms.maps.model.LatLng
import java.util.*

open abstract class PetRequest(
    var pet:Pet,
    var state:State,
    var creationDate: Date,
    var resolvedDate: Date?,
    var coordinates:LatLng,
    var requestCreator: User,
    var requestConsumer: User
) {

}
