package com.ort.altoqueperro.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.R
import com.ort.altoqueperro.activities.MainActivity.Companion.score
import com.ort.altoqueperro.activities.Score
import com.ort.altoqueperro.adapter.SimilarPetsAdapter
import com.ort.altoqueperro.entities.Pet
import com.ort.altoqueperro.repos.PetRepository
import com.ort.altoqueperro.repos.ShelterRepository
import com.ort.altoqueperro.viewmodels.PetLostSearchSimilaritiesViewModel

class PetLostSearchSimilarities : Fragment() {

    companion object {
        fun newInstance() = PetLostSearchSimilarities()
    }

    private lateinit var viewModel: PetLostSearchSimilaritiesViewModel
    var petRepository : PetRepository = PetRepository()
    private lateinit var recSimilarPets: RecyclerView
    private lateinit var noResult: Button
    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.pet_lost_search_similarities_fragment, container, false)
        noResult = v.findViewById(R.id.noResults)
        recSimilarPets = v.findViewById(R.id.recicler_view_search_for_similarities)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PetLostSearchSimilaritiesViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val petData = PetLostSearchSimilaritiesArgs.fromBundle(requireArguments()).pet
        recSimilarPets.adapter = SimilarPetsAdapter(lookForSimilarities(petData)){onSimilarPetsClick(it)}
    }

    override fun onStart() {
        super.onStart()
        noResult.setOnClickListener { noResult() }
        recSimilarPets.setHasFixedSize(true)
        recSimilarPets.layoutManager = LinearLayoutManager(context)
    }

    fun onSimilarPetsClick(pet: Pet) {
        println("hola")
    }

    fun noResult(){
        var action = PetLostSearchSimilaritiesDirections.actionPetLostSearchSimilaritiesToPetLostFinalMessage()
        v.findNavController().navigate(action);
    }

    fun lookForSimilarities(petLost: Pet): MutableList<Pet> {
        val similarPetsFound: MutableList<Pair<Pet, Int>> = mutableListOf()
        val result: MutableList<Pet> = mutableListOf()
        var minScoreValuePet: Pair<Pet?, Int> = Pair(null, 100)
        var currentScore: Int
        for (pet in petRepository.pets) {
            println(pet.name)
            if (pet.type == petLost.type) {
                if (similarPetsFound.count() < 3) {
                    currentScore = calculateScore(pet, petLost)
                    similarPetsFound.add(Pair(pet, currentScore))
                    if (currentScore < minScoreValuePet.second ) {
                        minScoreValuePet = Pair(pet, currentScore)
                    }
                } else {
                    println(pet.name)
                    currentScore = calculateScore(pet, petLost)
                    if (currentScore > minScoreValuePet.second ) {
                        similarPetsFound.add(Pair(pet, currentScore))
                        similarPetsFound.remove(minScoreValuePet)
                    }
                }
            }
        }
        for (pet in similarPetsFound) {
            result.add(pet.first)
        }
        return result
    }

    fun calculateScore(pet: Pet, petLost: Pet): Int {
        var finalScore: Int = 0
        if (pet.size == petLost.size) {
            finalScore = finalScore + score.size
        }
        if (pet.sex == petLost.sex) {
            finalScore = finalScore + score.sex
        }
        if (pet.coat == petLost.coat) {
            finalScore = finalScore + score.coat
        }
        if (pet.eyeColor == petLost.eyeColor) {
            finalScore = finalScore + score.eyeColor
        }
        return finalScore
    }

}