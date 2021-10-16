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
import com.ort.altoqueperro.viewmodels.PetFoundHostNoViewModel

class PetFoundHostNo : Fragment() {

    companion object {
        fun newInstance() = PetFoundHostNo()
    }

    private lateinit var viewModel: PetFoundHostNoViewModel
    private lateinit var v: View
    private lateinit var goToShelterList: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.pet_found_host_no_fragment, container, false)
        goToShelterList = v.findViewById(R.id.checkShelters)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PetFoundHostNoViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        goToShelterList.setOnClickListener { checkShelterList() }
    }

    fun checkShelterList(){
        var action = PetFoundHostNoDirections.actionPetFoundHostNoToShelterListFragment()
        v.findNavController().navigate(action);
    }

}