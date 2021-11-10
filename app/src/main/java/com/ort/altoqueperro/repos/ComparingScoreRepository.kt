package com.ort.altoqueperro.repos

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.entities.ComparingScore

class ComparingScoreRepository {
    val db = Firebase.firestore

   fun getScore(liveData: MutableLiveData<ComparingScore>){
        db.collection("config").document("config").get()
            .addOnSuccessListener {
                liveData.postValue(it.toObject())
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }
}