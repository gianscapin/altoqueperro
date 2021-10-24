package com.ort.altoqueperro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.altoqueperro.R
import com.ort.altoqueperro.adapter.SimilarPetsAdapter
import com.ort.altoqueperro.entities.PetScore
import com.ort.altoqueperro.viewmodels.PetLostSearchSimilaritiesViewModel

class PetLostSearchSimilarities : Fragment() {

    companion object {
        fun newInstance() = PetLostSearchSimilarities()
    }

    private lateinit var viewModel: PetLostSearchSimilaritiesViewModel
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
        val petRequestData = PetLostSearchSimilaritiesArgs.fromBundle(requireArguments()).petRequest
        viewModel.getPosibleMatches(petRequestData)
        viewModel.petRepository.observe(viewLifecycleOwner, Observer {

            recSimilarPets.adapter =
                SimilarPetsAdapter(viewModel.petRepository.value!!) { onSimilarPetsClick(it) }

        })

    }

    override fun onStart() {
        super.onStart()
        noResult.setOnClickListener { noResult() }
        recSimilarPets.setHasFixedSize(true)
        recSimilarPets.layoutManager = LinearLayoutManager(context)
    }

    fun onSimilarPetsClick(pet: PetScore) {
        val action =
            PetLostSearchSimilaritiesDirections.actionPetLostSearchSimilaritiesToFoundPetItemFragment()
        v.findNavController().navigate(action);
    }

    fun noResult() {
        var action =
            PetLostSearchSimilaritiesDirections.actionPetLostSearchSimilaritiesToPetLostFinalMessage()
        v.findNavController().navigate(action);
    }

    /** DEJO ESTO PORLAS PERO MOVÍ LA LÓGICA AL "compareTo" DE LOS REQUESTS Y PETS**/
    /*fun lookForSimilarities(petRequest: PetRequest): MutableList<PetScore> {
        val petLost = petRequest.pet //esto desp no va

        val similarPetsFound: MutableList<PetScore> = mutableListOf()
        var minScoreValue = 100
        var currentScore: Int

        for (request in requestRepository.foundRequests) {
            println(request.pet.name)
            if (request.pet.type == petLost.type) {
                if (similarPetsFound.count() < 3) { //debería hacerse while
                    currentScore = calculateScore(request.pet, petLost)
                    similarPetsFound.add(PetScore(request, currentScore))
                    if (currentScore < minScoreValue ) {
                        minScoreValue = currentScore
                    }
                } else {
                    println(request.pet.name)
                    currentScore = calculateScore(request.pet, petLost)
                    if (currentScore > minScoreValue ) {
                        similarPetsFound.add(PetScore(request, currentScore))
                        similarPetsFound.remove(similarPetsFound.first { unit -> unit.request!! == request })
                    }
                }
            }
        }
        return similarPetsFound
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
    }*/

}