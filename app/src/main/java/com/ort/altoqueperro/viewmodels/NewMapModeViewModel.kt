package com.ort.altoqueperro.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.entities.LostPetRequest
import com.ort.altoqueperro.entities.Shelter
import com.ort.altoqueperro.entities.Vet
import com.ort.altoqueperro.repos.RescueCenterRepository
import com.ort.altoqueperro.utils.Notifications

class NewMapModeViewModel : ViewModel() {
    var petRepository: MutableLiveData<MutableList<LostPetRequest>> =
        MutableLiveData(mutableListOf())
    var vetsRepository: MutableLiveData<MutableList<Vet>> = MutableLiveData(mutableListOf())
    var sheltersRepository: MutableLiveData<MutableList<Shelter>> = MutableLiveData(mutableListOf())
    private val rescueCenterRepository: RescueCenterRepository = RescueCenterRepository()

    val db = Firebase.firestore
    val user = Firebase.auth.currentUser


    fun getLostPets() { //ToDo mover a repository
        val lostRequests: MutableList<LostPetRequest> = mutableListOf()
        db.collection("lostPetRequests").get()
            .addOnSuccessListener {
                for (request in it) {
                    //lostRequests.add(request.toObject<LostPetRequest>())
                    if (!Notifications.getNotificationPetLost()) {
                        val petRequest = request.toObject<LostPetRequest>()
                        petRequest.id = request.id
                        lostRequests.add(petRequest)
                    } else {
                        val petRequest = request.toObject<LostPetRequest>()
                        petRequest.id = request.id
                        val userId: String = user?.uid.toString()
                        val petCreatorId: String = petRequest.requestCreator
                        if (petCreatorId != userId) {
                            lostRequests.add(petRequest)
                        }
                    }
                }
                petRepository.value = lostRequests
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }

    fun getShelters() {
        rescueCenterRepository.getShelters(sheltersRepository)
    }

    fun getVets() {
        rescueCenterRepository.getVets(vetsRepository)

    }

}
