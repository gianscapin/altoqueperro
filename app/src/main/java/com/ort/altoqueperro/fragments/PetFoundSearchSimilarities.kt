package com.ort.altoqueperro.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.PetScore
import com.ort.altoqueperro.repos.RequestRepository
import com.ort.altoqueperro.viewmodels.PetFoundSearchSimilaritiesViewModel

class PetFoundSearchSimilarities : Fragment() {

    companion object {
        fun newInstance() = PetFoundSearchSimilarities()
    }

    private lateinit var viewModel: PetFoundSearchSimilaritiesViewModel
    var requestRepository : RequestRepository = RequestRepository()
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
    }

    override fun onStart() {
        super.onStart()
        petFoundNoResults.setOnClickListener { noResult() }
    }

    fun onSimilarPetsClick(pet: PetScore) {
        val action =
            PetFoundSearchSimilaritiesDirections.actionPetFoundSearchSimilaritiesToFoundPetItemFragment(null, pet)
        v.findNavController().navigate(action);
    }

    fun noResult(){
        var action = PetFoundSearchSimilaritiesDirections.actionPetFoundSearchSimilaritiesToPetFoundHostQuestion()
        v.findNavController().navigate(action);
    }

}