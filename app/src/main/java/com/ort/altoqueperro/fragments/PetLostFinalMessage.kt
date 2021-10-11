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
import com.ort.altoqueperro.viewmodels.PetLostFinalMessageViewModel

class PetLostFinalMessage : Fragment() {

    companion object {
        fun newInstance() = PetLostFinalMessage()
    }

    private lateinit var viewModel: PetLostFinalMessageViewModel
    private lateinit var backToMenu: Button
    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.pet_lost_final_message_fragment, container, false)
        backToMenu = v.findViewById(R.id.backToMenu)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PetLostFinalMessageViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        backToMenu.setOnClickListener { backToMenu() }
    }

    fun backToMenu(){
        var action = PetLostFinalMessageDirections.actionPetLostFinalMessageToNewMapModeFragment()
        v.findNavController().navigate(action);
    }

}