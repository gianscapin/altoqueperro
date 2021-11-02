package com.ort.altoqueperro.repos

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.entities.Shelter
import com.ort.altoqueperro.entities.Vet

class RescueCenterRepository {
    val db = Firebase.firestore

    fun getVets(liveData: MutableLiveData<MutableList<Vet>>) {
        val vets: MutableList<Vet> = mutableListOf()
        db.collection("vets").get()
            .addOnSuccessListener {
                for (vet in it) {
                    vets.add(vet.toObject())
                }
                liveData.postValue(vets)
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }

    fun getShelters(liveData: MutableLiveData<MutableList<Shelter>>) {
        val shelters: MutableList<Shelter> = mutableListOf()
        db.collection("shelters").get()
            .addOnSuccessListener {
                for (shelter in it) {
                    shelters.add(shelter.toObject())
                }
                liveData.postValue(shelters)
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }
}