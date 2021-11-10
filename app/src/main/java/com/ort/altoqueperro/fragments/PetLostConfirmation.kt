package com.ort.altoqueperro.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.PetRequest
import com.ort.altoqueperro.viewmodels.PetLostViewModel

class PetLostConfirmation : Fragment() {

    companion object {
        fun newInstance() = PetLostConfirmation()
    }

    private lateinit var nextButton: Button
    private lateinit var txtCommentsValue: TextView
    private lateinit var txtDateValue: TextView
    private lateinit var txtFoundTitle: TextView
    private lateinit var txtPetEyeColorValue: TextView
    private lateinit var txtPetFurColorValue: TextView
    private lateinit var txtPetFurLengthValue: TextView

    //lateinit var txtPetNameValue: TextView
    private lateinit var txtPetNoseValue: TextView
    private lateinit var txtPetSexValue: TextView
    private lateinit var txtPetSizeValue: TextView
    private lateinit var txtPetTimeValue: TextView
    private lateinit var txtPetTypeValue: TextView
    lateinit var v: View

    private val viewModel: PetLostViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.pet_lost_confirmation_fragment, container, false)

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
            txtCommentsValue.text = it

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
            txtFoundTitle.text = "¡$it perdido!"
            txtPetTypeValue.text = it
        })

        viewModel.lostDate.observe(viewLifecycleOwner, {
            txtDateValue.text = it
        })

        viewModel.time.observe(viewLifecycleOwner, {
            txtPetTimeValue.text = it
        })

        return v
    }

    override fun onStart() {
        super.onStart()
        nextButton.setOnClickListener {
            val petRequest = viewModel.registerPet()
            lookForSimilarities(petRequest)
        }
    }

    private fun lookForSimilarities(petRequest: PetRequest) {
        val action =
            PetLostConfirmationDirections.actionPetLostConfirmationToPetLostSearchSimilarities(
                petRequest
            )
        v.findNavController().navigate(action);
    }


}