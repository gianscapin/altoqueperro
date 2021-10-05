package com.ort.altoqueperro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.ort.altoqueperro.R
import com.ort.altoqueperro.viewmodels.LostPetItemViewModel

class LostPetItemFragment : Fragment() {
    lateinit var v: View
    lateinit var lostPetName: TextView
    lateinit var lostPetState: TextView
    lateinit var lostPetImage: ImageView
    lateinit var btnVets: Button
    lateinit var btnShelters: Button

    companion object {
        fun newInstance() = LostPetItemFragment()
    }

    private lateinit var viewModel: LostPetItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.pet_item_fragment, container, false)
        lostPetName = v.findViewById(R.id.txtPetName)
        lostPetState = v.findViewById(R.id.txtPetState)
        lostPetImage = v.findViewById(R.id.imagePetDetail)
        btnVets = v.findViewById(R.id.btnVets)
        btnShelters = v.findViewById(R.id.btnShelters)

        return  v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LostPetItemViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lostPetData = LostPetItemFragmentArgs.fromBundle(requireArguments()).petData

        lostPetName.text = lostPetData.name
        lostPetState.text = lostPetData.state
        Glide.with(view.context).load(lostPetData.pictureUrl).into(lostPetImage)

        btnVets.setOnClickListener {
            val action = LostPetItemFragmentDirections.actionLostPetItemFragmentToVetListFragment()
            v.findNavController().navigate(action)
        }

        btnShelters.setOnClickListener {
            val action = LostPetItemFragmentDirections.actionLostPetItemFragmentToShelterListFragment()
            v.findNavController().navigate(action)
        }

    }

}