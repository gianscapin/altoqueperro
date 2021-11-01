package com.ort.altoqueperro.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.entities.FoundPetRequest
import com.ort.altoqueperro.entities.PetRequest
import com.ort.altoqueperro.entities.RequestScore

class PetLostSearchSimilaritiesViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var requestRepository: MutableLiveData<MutableList<RequestScore>> = MutableLiveData(mutableListOf())
    val db = Firebase.firestore
    lateinit var foundRequests: MutableList<FoundPetRequest>


    fun getPosibleMatches(petRequest: PetRequest){
        foundRequests = mutableListOf()
        db.collection("foundPetRequests").get()
            .addOnSuccessListener {
                for (request in it) {
                    val requestObject = request.toObject<FoundPetRequest>()
                    requestObject.id = request.id
                    foundRequests.add(requestObject)
                }
                requestRepository.value = lookForSimilarities(petRequest)
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: " + exception)
            }
    }

    fun lookForSimilarities(petRequest: PetRequest): MutableList<RequestScore> {

        val similarPetsFound: MutableList<RequestScore> = mutableListOf()
        var minScoreValue = 50 //ToDo que sea una variable modificable por el admin

        for (request in foundRequests) {
            val petScore = petRequest.comparePetTo(request)
            if (petScore >= minScoreValue) similarPetsFound.add( RequestScore(request,petScore))
        }
        return similarPetsFound
    }
}