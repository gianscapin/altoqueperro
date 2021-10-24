package com.ort.altoqueperro.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.entities.Vet

class VetListViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var vetsRepository: MutableLiveData<MutableList<Vet>> = MutableLiveData(mutableListOf())
    val db = Firebase.firestore


    fun getVets(){
        val vets: MutableList<Vet> = mutableListOf()
        db.collection("vets").get()
            .addOnSuccessListener {
                for (vet in it) {
                    vets.add(vet.toObject<Vet>())
                }
                vetsRepository.value = vets
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: " + exception)
            }
    }
}