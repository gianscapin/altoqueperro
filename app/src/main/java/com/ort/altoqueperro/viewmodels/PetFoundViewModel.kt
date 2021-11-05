package com.ort.altoqueperro.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.entities.FoundPetRequest
import com.ort.altoqueperro.fragments.PetFoundConfirmationDirections
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

    private val mutableDate = MutableLiveData<String>()
    val date: LiveData<String> get() = mutableDate

    fun setDate(value: String) {
        mutableDate.value = value
    }

    private val mutableTime = MutableLiveData<String>()
    val time: LiveData<String> get() = mutableTime

    fun setTime(value: String) {
        mutableTime.value = value
    }

//    val pet = Pet(null, petType, petSize, petSex, petFurColor, petFurLength, petNose )
//
//
//
//    fun registerPet() {
//        val petRequest = FoundPetRequest(pet, null, user!!.uid)
//        val action = PetFoundConfirmationDirections.actionPetFoundConfirmationToPetFoundSearchSimilarities()
//        v.findNavController().navigate(action)
//
//        db.collection("petsLost").document().set(data)
//    }








    fun validateStep1(): Boolean {
        return !mutablePetSex.value.isNullOrEmpty() && !mutablePetSize.value.isNullOrEmpty() && !mutablePetType.value.isNullOrEmpty()
    }

    fun validateStep2(): Boolean {
        return !mutablePetEyeColor.value.isNullOrEmpty() && !mutablePetFurColor.value.isNullOrEmpty() && !mutablePetFurLength.value.isNullOrEmpty() && !mutablePetNose.value.isNullOrEmpty()
    }

    fun validateStep3(): Boolean {
        return !mutableComments.value.isNullOrEmpty() && !mutableDate.value.isNullOrEmpty() && !mutableTime.value.isNullOrEmpty()
    }

}