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
import com.ort.altoqueperro.viewmodels.PetFoundHostYesViewModel

class PetFoundHostYes : Fragment() {

    companion object {
        fun newInstance() = PetFoundHostYes()
    }

    private lateinit var viewModel: PetFoundHostYesViewModel
    private lateinit var v: View
    private lateinit var backToMenu: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.pet_found_host_yes_fragment, container, false)
        backToMenu = v.findViewById(R.id.backToMenu2)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PetFoundHostYesViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        backToMenu.setOnClickListener { backToMenu() }
    }

    fun backToMenu(){
        var action = PetFoundHostYesDirections.actionPetFoundHostYesToNewMapModeFragment()
        v.findNavController().navigate(action);
    }

}