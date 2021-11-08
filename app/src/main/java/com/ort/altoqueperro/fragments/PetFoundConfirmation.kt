package com.ort.altoqueperro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.PetRequest
import com.ort.altoqueperro.viewmodels.PetFoundViewModel

class PetFoundConfirmation : Fragment() {

    companion object {
        fun newInstance() = PetFoundConfirmation()
    }

    lateinit var nextButton : Button
    lateinit var txtCommentsValue : TextView
    lateinit var txtDateValue : TextView
    lateinit var txtFoundTitle : TextView
    lateinit var txtPetEyeColorValue : TextView
    lateinit var txtPetFurColorValue : TextView
    lateinit var txtPetFurLengthValue : TextView
    lateinit var txtPetNoseValue : TextView
    lateinit var txtPetSexValue : TextView
    lateinit var txtPetSizeValue : TextView
    lateinit var txtPetTimeValue : TextView
    lateinit var txtPetTypeValue : TextView
    lateinit var v: View

    private val viewModel: PetFoundViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.pet_found_confirmation_fragment, container, false)

        nextButton = v.findViewById(R.id.btnPublish)

        txtCommentsValue = v.findViewById(R.id.txtCommentsValue)
        txtDateValue = v.findViewById(R.id.txtPetDateValue)
        txtFoundTitle = v.findViewById(R.id.txtFoundTitle)
        txtPetEyeColorValue = v.findViewById(R.id.txtPetEyeColorValue)
        txtPetFurColorValue = v.findViewById(R.id.txtPetFurColorValue)
        txtPetFurLengthValue = v.findViewById(R.id.txtPetFurLengthValue)
        txtPetNoseValue = v.findViewById(R.id.txtPetNoseValue)
        txtPetSexValue = v.findViewById(R.id.txtPetSexValue)
        txtPetSizeValue = v.findViewById(R.id.txtPetSizeValue)
        txtPetTypeValue = v.findViewById(R.id.txtPetTypeValue)
        txtPetTimeValue = v.findViewById(R.id.txtPetTimeValue)

        viewModel.comments.observe(viewLifecycleOwner, {
            txtCommentsValue.text  = if (it.isNullOrEmpty()) "Sin comentarios" else "no"
        })

        viewModel.petEyeColor.observe(viewLifecycleOwner, {
            txtPetEyeColorValue.text = it
        })

        viewModel.petFurColor.observe(viewLifecycleOwner, {
            txtPetFurColorValue.text = it
        })

        viewModel.petFurLength.observe(viewLifecycleOwner, {
            txtPetFurLengthValue.text = it
        })

        viewModel.petNose.observe(viewLifecycleOwner, {
            txtPetNoseValue.text = it
        })

        viewModel.petSex.observe(viewLifecycleOwner, {
            txtPetSexValue.text = it
        })

        viewModel.petSize.observe(viewLifecycleOwner, {
            txtPetSizeValue.text = it
        })

        viewModel.petType.observe(viewLifecycleOwner, {
            txtFoundTitle.text = "¡$it encontrado!"
            txtPetTypeValue.text = it
        })

        viewModel.lostDate.observe(viewLifecycleOwner, {
            txtDateValue.text = it
        })

        return v
    }

    override fun onStart() {
        super.onStart()

        nextButton.setOnClickListener {
            var petRequest = viewModel.registerPet()
            lookForSimilarities(petRequest)
        }
    }

    fun lookForSimilarities(petRequest : PetRequest ) {
        var action = PetFoundConfirmationDirections.actionPetFoundConfirmationToPetFoundSearchSimilarities(petRequest)
        v.findNavController().navigate(action);
    }




}
