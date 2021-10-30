package com.ort.altoqueperro.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.entities.FoundPetRequest
import com.ort.altoqueperro.entities.LostPetRequest

class LostPetListViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var petRepository: MutableLiveData<MutableList<LostPetRequest>> = MutableLiveData(mutableListOf())
    var foundPetRepository: MutableLiveData<MutableList<FoundPetRequest>> = MutableLiveData(mutableListOf())
    var myLostPets: MutableList<LostPetRequest> = mutableListOf()
    var othersLostPets: MutableList<LostPetRequest> = mutableListOf()
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
    fun getOwnFoundPets(){
        var idUserLogged = FirebaseAuth.getInstance().currentUser?.uid.toString()
        val foundRequests: MutableList<FoundPetRequest> = mutableListOf()
        db.collection("foundPetRequests")
            .whereEqualTo("requestCreator",idUserLogged).get()
            .addOnSuccessListener {
                for (request in it) {
                    foundRequests.add(request.toObject<FoundPetRequest>())
                }
                foundPetRepository.value = foundRequests
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: " + exception)
            }
    }
    fun distribute(){
        var idUserLogged = FirebaseAuth.getInstance().currentUser?.uid.toString()
        myLostPets.clear()
        othersLostPets.clear()
        if (petRepository.value!=null) {
            petRepository.value!!.forEach(){
                if (it.requestCreator == idUserLogged) {
                    myLostPets.add(it)
                } else {
                    othersLostPets.add(it)
                }
            }
        }
    }
}