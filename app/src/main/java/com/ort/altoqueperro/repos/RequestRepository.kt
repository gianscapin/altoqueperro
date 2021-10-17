package com.ort.altoqueperro.repos

import com.ort.altoqueperro.entities.FoundPetRequest
import com.ort.altoqueperro.entities.LostPetRequest
import java.util.*

class RequestRepository {
    var lostRequests: MutableList<LostPetRequest> = mutableListOf()
    var foundRequests: MutableList<FoundPetRequest> = mutableListOf()


    init {
        createRequestsDatabase();
    }

    private fun createRequestsDatabase() {
        PetRepository().pets.forEach {
            foundRequests.add(
                FoundPetRequest(
                    it,
                    "open",
                    Calendar.getInstance().time,
                    null,
                    "coordinadas",
                    UserRepository().getRandomUser(),
                    null
                )
            )
        }
    }

}
