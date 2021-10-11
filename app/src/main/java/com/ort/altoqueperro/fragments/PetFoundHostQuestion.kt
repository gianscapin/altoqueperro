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
import com.ort.altoqueperro.viewmodels.PetFoundHostQuestionViewModel
import com.ort.altoqueperro.viewmodels.PetFoundSearchSimilaritiesViewModel

class PetFoundHostQuestion : Fragment() {

    companion object {
        fun newInstance() = PetFoundHostQuestion()
    }

    private lateinit var viewModel: PetFoundHostQuestionViewModel
    private lateinit var yesHost: Button
    private lateinit var noHost: Button
    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.pet_found_host_question_fragment, container, false)
        yesHost = v.findViewById(R.id.yesHost)
        noHost = v.findViewById(R.id.noHost)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PetFoundHostQuestionViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        yesHost.setOnClickListener { yesHost() }
        noHost.setOnClickListener { noHost() }
    }

    fun yesHost(){
        var action = PetFoundHostQuestionDirections.actionPetFoundHostQuestionToPetFoundHostYes()
        v.findNavController().navigate(action);
    }

    fun noHost(){
        var action = PetFoundHostQuestionDirections.actionPetFoundHostQuestionToPetFoundHostNo()
        v.findNavController().navigate(action);
    }

}