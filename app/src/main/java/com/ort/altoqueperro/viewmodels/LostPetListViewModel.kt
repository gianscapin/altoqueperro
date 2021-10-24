package com.ort.altoqueperro.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.entities.LostPetRequest

class LostPetListViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var petRepository: MutableLiveData<MutableList<LostPetRequest>> = MutableLiveData(mutableListOf())
    val db = Firebase.firestore


    fun getLostPets(){
        val lostRequests: MutableList<LostPetRequest> = mutableListOf()
        db.collection("lostPetRequests").get()
        .addOnSuccessListener {
            for (request in it) {
                lostRequests.add(request.toObject<LostPetRequest>())
            }
            petRepository.value = lostRequests
        }
            .addOnFailureListener { exception ->
                println("Error getting documents: " + exception)
            }
    }
}