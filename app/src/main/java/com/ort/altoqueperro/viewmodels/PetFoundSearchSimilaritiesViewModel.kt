package com.ort.altoqueperro.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.entities.LostPetRequest
import com.ort.altoqueperro.entities.PetRequest
import com.ort.altoqueperro.entities.PetScore

class PetFoundSearchSimilaritiesViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var petRepository: MutableLiveData<MutableList<PetScore>> = MutableLiveData(mutableListOf())
    val db = Firebase.firestore
    lateinit var foundRequests: MutableList<LostPetRequest>


    fun getPosibleMatches(petRequest: PetRequest){
        foundRequests = mutableListOf()
        db.collection("lostPetRequests").get()
            .addOnSuccessListener {
                for (request in it) {
                    foundRequests.add(request.toObject<LostPetRequest>())
                }
                petRepository.value = lookForSimilarities(petRequest)
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: " + exception)
            }
    }

    fun lookForSimilarities(petRequest: PetRequest): MutableList<PetScore> {

        val similarPetsFound: MutableList<PetScore> = mutableListOf()
        var minScoreValue = 50

        for (request in foundRequests) {
            val petScore = petRequest.comparePetTo(request)
            if (petScore >= minScoreValue) similarPetsFound.add( PetScore(request,petScore))
        }
        return similarPetsFound
    }
}