package com.ort.altoqueperro.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.R
import com.ort.altoqueperro.viewmodels.PetLostSearchSimilaritiesViewModel

class PetLostSearchSimilarities : Fragment() {

    companion object {
        fun newInstance() = PetLostSearchSimilarities()
    }

    private lateinit var viewModel: PetLostSearchSimilaritiesViewModel
    private lateinit var noResult: Button
    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.pet_lost_search_similarities_fragment, container, false)
        noResult = v.findViewById(R.id.noResults)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PetLostSearchSimilaritiesViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        noResult.setOnClickListener { noResult() }
    }

    fun noResult(){
        var action = PetLostSearchSimilaritiesDirections.actionPetLostSearchSimilaritiesToPetLostFinalMessage()
        v.findNavController().navigate(action);
    }

}