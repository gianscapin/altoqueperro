package com.ort.altoqueperro.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.entities.Shelter
import com.ort.altoqueperro.repos.RescueCenterRepository

class ShelterListViewModel : ViewModel() {
    var sheltersLiveData: MutableLiveData<MutableList<Shelter>> = MutableLiveData(mutableListOf())
    val db = Firebase.firestore


    fun getShelters2() {
        RescueCenterRepository().getShelters(sheltersLiveData)
    }
    fun getShelters(){ //ToDo no va
        val shelters: MutableList<Shelter> = mutableListOf()
        db.collection("shelters").get()
            .addOnSuccessListener {
                for (shelter in it) {
                    shelters.add(shelter.toObject<Shelter>())
                }
                sheltersLiveData.value = shelters
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: " + exception)
            }
    }
}