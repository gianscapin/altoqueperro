package com.ort.altoqueperro.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ort.altoqueperro.entities.ComparingScore
import com.ort.altoqueperro.entities.FoundPetRequest
import com.ort.altoqueperro.entities.PetRequest
import com.ort.altoqueperro.entities.RequestScore
import com.ort.altoqueperro.repos.ComparingScoreRepository
import com.ort.altoqueperro.repos.RequestRepository

class PetLostSearchSimilaritiesViewModel : ViewModel() {
    var comparingScoreLiveData: MutableLiveData<ComparingScore> = MutableLiveData()
    var foundPetRequestRepository: MutableLiveData<MutableList<FoundPetRequest>> =
        MutableLiveData(mutableListOf())

    fun getFoundPetRequests() {
        RequestRepository.getAllFoundPetRequests(foundPetRequestRepository)
    }

    fun lookForSimilarities(petRequest: PetRequest): MutableList<RequestScore> {

        val similarPetsFound: MutableList<RequestScore> = mutableListOf()
        val minScoreValue = comparingScoreLiveData.value!!.minScoreValue

        foundPetRequestRepository.value!!.forEach {
            val petScore = petRequest.comparePetTo(it, comparingScoreLiveData.value!!)
            if (petScore >= minScoreValue) similarPetsFound.add(RequestScore(it, petScore))
        }
        return similarPetsFound
    }

    fun getComparingScore() {
        ComparingScoreRepository.getScore(comparingScoreLiveData)
    }
}