package com.ort.altoqueperro.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.entities.FoundPetRequest
import com.ort.altoqueperro.entities.LostPetRequest
import com.ort.altoqueperro.entities.Shelter
import com.ort.altoqueperro.entities.Vet
import com.ort.altoqueperro.repos.RequestRepository
import com.ort.altoqueperro.utils.Notifications

class NewMapModeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var petRepository: MutableLiveData<MutableList<LostPetRequest>> = MutableLiveData(mutableListOf())
    var vetsRepository: MutableLiveData<MutableList<Vet>> = MutableLiveData(mutableListOf())
    var sheltersRepository: MutableLiveData<MutableList<Shelter>> = MutableLiveData(mutableListOf())

    val db = Firebase.firestore
    val user = Firebase.auth.currentUser


    fun getLostPets(){
        val lostRequests: MutableList<LostPetRequest> = mutableListOf()
        db.collection("lostPetRequests").get()
            .addOnSuccessListener {
                for (request in it) {
                    //lostRequests.add(request.toObject<LostPetRequest>())
                    if(!Notifications.getNotificationPetLost()){
                        val petRequest = request.toObject<LostPetRequest>()
                        petRequest.id = request.id
                        lostRequests.add(petRequest)
                    }else{
                        val petRequest = request.toObject<LostPetRequest>()
                        petRequest.id = request.id
                        val userId: String = user?.uid.toString()
                        val petCreatorId: String = petRequest.requestCreator
                        if(!petCreatorId.equals(userId)){
                            lostRequests.add(petRequest)
                        }
                    }
                }
                petRepository.value = lostRequests
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: " + exception)
            }
    }
    fun getShelters() {
        val shelters: MutableList<Shelter> = mutableListOf()
        db.collection("shelters").get()
            .addOnSuccessListener {
                for (shelter in it) {
                    val shelterObj = shelter.toObject<Shelter>()
                    shelters.add(shelterObj)
                }
                sheltersRepository.value = shelters
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: " + exception)
            }
    }

    fun getVets() {
        val vets: MutableList<Vet> = mutableListOf()
        db.collection("vets").get()
            .addOnSuccessListener {
                for (vet in it) {
                    val vetObj = vet.toObject<Vet>()
                    vets.add(vetObj)
                }
                vetsRepository.value = vets
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: " + exception)
            }
    }

    fun getPetRequests(): MutableList<FoundPetRequest> {
        var requests = RequestRepository().foundRequests
        var i = 0
        return requests
    }
}
