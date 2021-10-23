package com.ort.altoqueperro.repos

import com.ort.altoqueperro.entities.FoundPetRequest
import com.ort.altoqueperro.entities.LostPetRequest
import com.ort.altoqueperro.entities.State
import java.util.*

class RequestRepository {
    var foundRequests: MutableList<FoundPetRequest> = mutableListOf()


    init {
        createRequestsDatabase();
    }

    private fun createRequestsDatabase() {
        PetRepository().pets.forEach {
            foundRequests.add(
                FoundPetRequest(
                    it,
                    State.OPEN,
                    Calendar.getInstance().time,
                    null,
                    null,
                    UserRepository().getRandomUser(),
                    null
                )
            )
        }
    }

}
