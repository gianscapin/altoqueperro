package com.ort.altoqueperro.repos

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.entities.FoundPetRequest
import com.ort.altoqueperro.entities.LostPetRequest
import com.ort.altoqueperro.entities.State

class RequestRepository {
    val db = Firebase.firestore

    fun getAllLostPetRequests(liveData: MutableLiveData<MutableList<LostPetRequest>>) {
        val lostRequests: MutableList<LostPetRequest> = mutableListOf()
        db.collection("lostPetRequests")
            .whereNotEqualTo("state", State.CLOSED.ordinal)
            .get()
            .addOnSuccessListener {
                for (request in it) {
                    val requestObject = request.toObject<LostPetRequest>()
                    lostRequests.add(requestObject)
                }
                liveData.postValue(lostRequests)
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }

    fun getAllFoundPetRequests(liveData: MutableLiveData<MutableList<FoundPetRequest>>) {
        val foundPetRequests: MutableList<FoundPetRequest> = mutableListOf()
        db.collection("foundPetRequests")
            .whereEqualTo("state", State.OPEN.ordinal)
            .get()
            .addOnSuccessListener {
                for (request in it) {
                    val requestObject = request.toObject<FoundPetRequest>()
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
        if (petRequest.id.isEmpty()) {
            val document = db.collection("lostPetRequests").document()
            petRequest.id = document.id
            document.set(petRequest)
        } else db.collection("lostPetRequests").document(petRequest.id).set(petRequest)
    }

    fun saveFoundPetRequest(petRequest: FoundPetRequest) {
        if (petRequest.id.isEmpty()) {
            val document = db.collection("foundPetRequests").document()
            petRequest.id = document.id
            document.set(petRequest)
        } else db.collection("foundPetRequests").document(petRequest.id).set(petRequest)
    }


}
