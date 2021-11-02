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
import com.ort.altoqueperro.entities.RequestScore
import com.ort.altoqueperro.viewmodels.PetFoundSearchSimilaritiesViewModel

class PetFoundSearchSimilarities : Fragment() {

    companion object {
        fun newInstance() = PetFoundSearchSimilarities()
    }

    private lateinit var viewModel: PetFoundSearchSimilaritiesViewModel
    private lateinit var recSimilarPets: RecyclerView
    private lateinit var noResult: Button
    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.pet_found_search_similarities_fragment, container, false)
        noResult = v.findViewById(R.id.noPetFoundResults)
        recSimilarPets = v.findViewById(R.id.recicler_view_search_for_petFound_similarities)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PetFoundSearchSimilaritiesViewModel::class.java)
        // TODO: Use the ViewModel
        val petRequestData = PetFoundSearchSimilaritiesArgs.fromBundle(requireArguments()).petRequest
        viewModel.getPosibleMatches(petRequestData)
        viewModel.requestRepository.observe(viewLifecycleOwner, Observer {

            recSimilarPets.adapter = SimilarPetsAdapter(viewModel.requestRepository.value!!) { onSimilarPetsClick(it) }

        })
    }

    override fun onStart() {
        super.onStart()
        noResult.setOnClickListener { noResult() }
        recSimilarPets.setHasFixedSize(true)
        recSimilarPets.layoutManager = LinearLayoutManager(context)
    }

    fun onSimilarPetsClick(request: RequestScore) {
        /*val action =
            PetFoundSearchSimilaritiesDirections.actionPetFoundSearchSimilaritiesToLostPetItemFragment(null, pet)
        v.findNavController().navigate(action);*/
    }

    fun noResult(){
        var action = PetFoundSearchSimilaritiesDirections.actionPetFoundSearchSimilaritiesToPetFoundHostQuestion()
        v.findNavController().navigate(action);
    }

    /** DEJO ESTO PORLAS PERO MOVÍ LA LÓGICA AL "compareTo" DE LOS REQUESTS Y PETS**/
    /* fun lookForSimilarities(petRequest: PetRequest): MutableList<PetScore> {
         val petFound = petRequest.pet //esto desp no va

         val similarPetsLost: MutableList<PetScore> = mutableListOf()
         var minScoreValue = 100
         var currentScore: Int

         for (request in requestRepository.lostRequests) {
             println(request.pet.name)
             if (request.pet.type == petFound.type) {
                 if (similarPetsLost.count() < 3) { //debería hacerse while
                     /**        petRequest.comparePetTo(pet)  esta sería la función para comparar mascostas más adelante**/
                     currentScore = calculateScore(request.pet, petFound)
                     similarPetsLost.add(PetScore(request, currentScore)) //sacar el pet después. pongo request en null para hace más fácil
                     if (currentScore < minScoreValue ) {
                         minScoreValue = currentScore
                     }
                 } else {
                     println(request.pet.name)
                     currentScore = calculateScore(request.pet, petFound)
                     if (currentScore > minScoreValue ) {
                         similarPetsLost.add(PetScore(request, currentScore))
                         similarPetsLost.remove(similarPetsLost.first { unit -> unit.request!! == request })
                     }
                 }
             }
         }
         return similarPetsLost
     }

    fun calculateScore(pet: Pet, petLost: Pet): Int {
        var finalScore: Int = 0
        if (pet.size == petLost.size) {
            finalScore = finalScore + MainActivity.score.size
        }
        if (pet.sex == petLost.sex) {
            finalScore = finalScore + MainActivity.score.sex
        }
        if (pet.coat == petLost.coat) {
            finalScore = finalScore + MainActivity.score.coat
        }
        if (pet.eyeColor == petLost.eyeColor) {
            finalScore = finalScore + MainActivity.score.eyeColor
        }
        return finalScore
    }*/

}