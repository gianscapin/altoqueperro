package com.ort.altoqueperro.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.entities.Shelter

class ShelterListViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var sheltersRepository: MutableLiveData<MutableList<Shelter>> = MutableLiveData(mutableListOf())
    val db = Firebase.firestore


    fun getShelters(){
        val shelters: MutableList<Shelter> = mutableListOf()
        db.collection("shelters").get()
            .addOnSuccessListener {
                for (shelter in it) {
                    shelters.add(shelter.toObject<Shelter>())
                }
                sheltersRepository.value = shelters
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: " + exception)
            }
    }
}