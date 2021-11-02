package com.ort.altoqueperro.repos

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.entities.FoundPetRequest
import com.ort.altoqueperro.entities.LostPetRequest
import com.ort.altoqueperro.entities.State
import java.util.*

class RequestRepository {
    var foundRequests: MutableList<FoundPetRequest> = mutableListOf() //ToDo no va
    private var lostRequests: MutableList<LostPetRequest> = mutableListOf() //ToDo no va
    val db = Firebase.firestore

    init {
        createRequestsDatabase() //ToDo no va
    }

    fun getLostPetRequests(liveData: MutableLiveData<MutableList<LostPetRequest>>) {
        val lostRequests: MutableList<LostPetRequest> = mutableListOf()
        db.collection("lostPetRequests")
            .whereNotEqualTo("state", State.CLOSED.ordinal)
            .get()
            .addOnSuccessListener {
                for (request in it) {
                    val requestObject = request.toObject<LostPetRequest>()
                    requestObject.id = request.id
                    lostRequests.add(requestObject)
                }
                liveData.postValue(lostRequests)
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }

    fun getFoundPetRequests(liveData: MutableLiveData<MutableList<FoundPetRequest>>) {
        val foundPetRequests: MutableList<FoundPetRequest> = mutableListOf()
        db.collection("foundPetRequests")
            .whereNotEqualTo("state", State.CLOSED.ordinal)
            .get()
            .addOnSuccessListener {
                for (request in it) {
                    val requestObject = request.toObject<FoundPetRequest>()
                    requestObject.id = request.id
                    foundPetRequests.add(requestObject)
                }
                liveData.postValue(foundPetRequests)
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }

    fun getLostPetRequest(liveData: MutableLiveData<LostPetRequest>, request: String) {
        db.collection("lostPetRequests").document(request).get()
            .addOnSuccessListener {
                liveData.postValue(it.toObject())
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }

    fun getFoundPetRequest(liveData: MutableLiveData<FoundPetRequest>, request: String) {
        db.collection("foundPetRequests").document(request).get()
            .addOnSuccessListener {
                liveData.postValue(it.toObject())
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }

    fun saveLostPetRequest(petRequest: LostPetRequest) {
        db.collection("lostPetRequests").document(petRequest.id).set(petRequest)
    }

    fun saveFoundPetRequest(petRequest: FoundPetRequest) {
        db.collection("foundPetRequests").document(petRequest.id).set(petRequest)
    }

    private fun createRequestsDatabase() { //ToDo no va
        PetRepository().pets.forEach {
            foundRequests.add(
                FoundPetRequest(
                    "",
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
                    "",
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
