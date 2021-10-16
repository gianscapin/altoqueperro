package com.ort.altoqueperro.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.ort.altoqueperro.R
import com.ort.altoqueperro.viewmodels.PetFoundSearchSimilaritiesViewModel

class PetFoundSearchSimilarities : Fragment() {

    companion object {
        fun newInstance() = PetFoundSearchSimilarities()
    }

    private lateinit var viewModel: PetFoundSearchSimilaritiesViewModel
    private lateinit var petFoundNoResults: Button
    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.pet_found_search_similarities_fragment, container, false)
        petFoundNoResults = v.findViewById(R.id.petFoundNoResults)
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

    fun noResult(){
        var action = PetFoundSearchSimilaritiesDirections.actionPetFoundSearchSimilaritiesToPetFoundHostQuestion()
        v.findNavController().navigate(action);
    }

}