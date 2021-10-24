package com.ort.altoqueperro.repos

import com.ort.altoqueperro.entities.FoundPetRequest
import com.ort.altoqueperro.entities.LostPetRequest
import com.ort.altoqueperro.entities.State
import java.util.*

class RequestRepository {
    var foundRequests: MutableList<FoundPetRequest> = mutableListOf()
    var lostRequests: MutableList<LostPetRequest> = mutableListOf()

    init {
        createRequestsDatabase();
    }

    private fun createRequestsDatabase() {
        PetRepository().pets.forEach {
            foundRequests.add(
                FoundPetRequest(
                    it,
                    State.OPEN.ordinal,
                    Calendar.getInstance().time,
                    null,
                    null,
                    UserRepository().getRandomUser().toString(),
                    null,
                    null
                )
            )
            lostRequests.add(
                LostPetRequest(
                    it,
                    State.OPEN.ordinal,
                    Calendar.getInstance().time,
                    null,
                    null,
                    UserRepository().getRandomUser().toString(),
                    null
                )
            )
        }
    }

}
