package com.ort.altoqueperro.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.entities.FoundPetRequest
import com.ort.altoqueperro.entities.Pet
import com.ort.altoqueperro.entities.PetRequest
import com.ort.altoqueperro.entities.State
import com.ort.altoqueperro.fragments.PetFoundDirections
import java.util.*

class PetFoundViewModel : ViewModel() {

    val db = Firebase.firestore

    private val mutableComments = MutableLiveData<String>()
    val comments: LiveData<String> get() = mutableComments

    fun setComments(value: String) {
        mutableComments.value = value
    }

    private val mutablePetEyeColor = MutableLiveData<String>()
    val petEyeColor: LiveData<String> get() = mutablePetEyeColor

    fun setPetEyeColor(value: String) {
        mutablePetEyeColor.value = value
    }

    private val mutablePetFurColor = MutableLiveData<String>()
    val petFurColor: LiveData<String> get() = mutablePetFurColor

    fun setPetFurColor(value: String) {
        mutablePetFurColor.value = value
    }

    private val mutablePetFurLength = MutableLiveData<String>()
    val petFurLength: LiveData<String> get() = mutablePetFurLength

    fun setPetFurLength(value: String) {
        mutablePetFurLength.value = value
    }

    private val mutablePetNose = MutableLiveData<String>()
    val petNose: LiveData<String> get() = mutablePetNose

    fun setPetNose(value: String) {
        mutablePetNose.value = value
    }

    private val mutablePetSex = MutableLiveData<String>()
    val petSex: LiveData<String> get() = mutablePetSex

    fun setPetSex(value: String) {
        mutablePetSex.value = value
    }

    private val mutablePetSize = MutableLiveData<String>()
    val petSize: LiveData<String> get() = mutablePetSize

    fun setPetSize(value: String) {
        mutablePetSize.value = value
    }

    private val mutablePetType = MutableLiveData<String>()
    val petType: LiveData<String> get() = mutablePetType

    fun setPetType(value: String) {
        mutablePetType.value = value
    }

    private val mutableLostDate = MutableLiveData<String>()
    val lostDate: LiveData<String> get() = mutableLostDate

    fun setLostDate(value: String) {
        mutableLostDate.value = value
    }

    private val mutableTime = MutableLiveData<String>()
    val time: LiveData<String> get() = mutableTime

    fun setTime(value: String) {
        mutableTime.value = value
    }

    fun registerPet(): PetRequest {
        val user = Firebase.auth.currentUser
        val pet = Pet(null,
            petType.toString(),
            petSize.toString(),
            petSex.toString(),
            petNose.toString(),
            petFurLength.toString(),
            petFurColor.toString(),
            petEyeColor.toString(),
            comments.toString(),
            lostDate.toString())

        val petRequest = FoundPetRequest(
            pet,
            null,
            user!!.uid,
        )
        db.collection("foundPetRequests").document().set(petRequest)
        return petRequest
      }


    fun validateStep1(): Boolean {
        return !mutablePetSex.value.isNullOrEmpty() && !mutablePetSize.value.isNullOrEmpty() && !mutablePetType.value.isNullOrEmpty()
    }

    fun validateStep2(): Boolean {
        return !mutablePetEyeColor.value.isNullOrEmpty() && !mutablePetFurColor.value.isNullOrEmpty() && !mutablePetFurLength.value.isNullOrEmpty() && !mutablePetNose.value.isNullOrEmpty()
    }

    fun validateStep3(): Boolean {
        return !mutableTime.value.isNullOrEmpty()
    }

}