package com.ort.altoqueperro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ort.altoqueperro.R
import com.ort.altoqueperro.viewmodels.PetFoundViewModel

class PetFound2 : Fragment(), AdapterView.OnItemSelectedListener  {

    companion object {
        fun newInstance() = PetFound2()
    }

    lateinit var nextButton: Button
    lateinit var petEyeColorSpinner: Spinner
    lateinit var petFurColorSpinner: Spinner
    lateinit var petFurLengthSpinner: Spinner
    lateinit var petNosesSpinner: Spinner
    lateinit var v: View
    lateinit var rootLayout: ConstraintLayout
    private val viewModel: PetFoundViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.pet_found_2_fragment, container, false)

        nextButton = v.findViewById(R.id.btnNext)

        rootLayout = v.findViewById(R.id.pet_found_root_layout_2)

        petEyeColorSpinner = v.findViewById(R.id.pet_eye_color_spinner)
        petFurColorSpinner = v.findViewById(R.id.pet_fur_color_spinner)
        petFurLengthSpinner = v.findViewById(R.id.pet_fur_length_spinner)
        petNosesSpinner = v.findViewById(R.id.pet_nose_spinner)

        configureSpinner(petEyeColorSpinner, R.array.pet_eye_colors)
        configureSpinner(petFurColorSpinner, R.array.pet_fur_colors)
        configureSpinner(petFurLengthSpinner, R.array.pet_fur_lengths)
        configureSpinner(petNosesSpinner, R.array.pet_noses)

        return v
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        when (parent.id) {
            petEyeColorSpinner.id -> viewModel.setPetEyeColor(petEyeColorSpinner.selectedItem.toString())
            petFurColorSpinner.id -> viewModel.setPetFurColor(petFurColorSpinner.selectedItem.toString())
            petFurLengthSpinner.id -> viewModel.setPetFurLength(petFurLengthSpinner.selectedItem.toString())
            petNosesSpinner.id -> viewModel.setPetNose(petNosesSpinner.selectedItem.toString())
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

    override fun onStart() {
        super.onStart()

        nextButton.setOnClickListener {
            if (viewModel.validateStep2()) {
                val action = PetFound2Directions.actionPetFound2ToPetFound3()
                v.findNavController().navigate(action)
            }
            else {
                Snackbar.make(rootLayout, "* Campos obligatorios", Snackbar.LENGTH_SHORT).show()

            }
        }
    }

    private fun configureSpinner(spinner: Spinner, textArrayResId: Int) {
        spinner.setSelection(0, false)
        spinner.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            v.context, textArrayResId, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }
}