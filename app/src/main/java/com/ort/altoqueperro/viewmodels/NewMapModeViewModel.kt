package com.ort.altoqueperro.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.entities.FoundPetRequest
import com.ort.altoqueperro.entities.LostPetRequest
import com.ort.altoqueperro.entities.Shelter
import com.ort.altoqueperro.entities.Vet
import com.ort.altoqueperro.repos.RequestRepository

class NewMapModeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var petRepository: MutableLiveData<MutableList<LostPetRequest>> =
        MutableLiveData(mutableListOf())
    var vetsRepository: MutableLiveData<MutableList<Vet>> = MutableLiveData(mutableListOf())
    var sheltersRepository: MutableLiveData<MutableList<Shelter>> = MutableLiveData(mutableListOf())

    val db = Firebase.firestore


    fun getLostPets() {
        val lostRequests: MutableList<LostPetRequest> = mutableListOf()
        db.collection("lostPetRequests").get()
            .addOnSuccessListener {
                for (request in it) {
                    val lostPetRequest = request.toObject<LostPetRequest>()
                    lostPetRequest.coordinates =
                        LatLng(-34.609062, -58.427683 + (it.indexOf(request) * 0.001))
                    lostRequests.add(lostPetRequest)
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
                    shelterObj.coordinates =
                        LatLng(-34.609062, -58.427683 + (it.indexOf(shelter) * 0.001))
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
                    vetObj.coordinates =
                        LatLng(-34.609062, -58.427683 + (it.indexOf(vet) * 0.001))
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
        requests.forEach {
            it.coordinates = LatLng(-34.609062, -58.427683 + (i * 0.001))
            i++
        }

        return requests
    }
}
