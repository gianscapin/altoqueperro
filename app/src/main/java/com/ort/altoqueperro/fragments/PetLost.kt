package com.ort.altoqueperro.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ort.altoqueperro.R
import com.ort.altoqueperro.viewmodels.PetLostViewModel

class PetLost : Fragment() {

    companion object {
        fun newInstance() = PetLost()
    }

    private lateinit var viewModel: PetLostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pet_lost_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PetLostViewModel::class.java)
        // TODO: Use the ViewModel
    }

}