package com.ort.altoqueperro.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ort.altoqueperro.R
import com.ort.altoqueperro.viewmodels.PetLostViewModel

class PetLost : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance() = PetLost()
    }

    lateinit var petName: TextView
    lateinit var petType: Spinner
    //lateinit var petTypeValue: String
    //lateinit var petSex: RadioButton
    //lateinit var petSize: RadioButton

    lateinit var nextButton: Button

    lateinit var v: View
    private lateinit var rootLayout: ConstraintLayout

    private val viewModel: PetLostViewModel by activityViewModels()

    //private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.pet_lost_1_fragment, container, false)
        nextButton = v.findViewById(R.id.btnNext)
        rootLayout = v.findViewById(R.id.pet_lost_root_layout_1)

        petName = v.findViewById(R.id.petName)
        petName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setPetName(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        petType = v.findViewById(R.id.pet_types_spinner)
        petType.setSelection(0, false)
        petType.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            v.context, R.array.pet_types, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            petType.adapter = adapter
        }

        (v.findViewById(R.id.radio_big_pet) as RadioButton).setOnClickListener(this)
        (v.findViewById(R.id.radio_medium_pet) as RadioButton).setOnClickListener(this)
        (v.findViewById(R.id.radio_small_pet) as RadioButton).setOnClickListener(this)
        (v.findViewById(R.id.radio_mini_pet) as RadioButton).setOnClickListener(this)

        (v.findViewById(R.id.radio_male) as RadioButton).setOnClickListener(this)
        (v.findViewById(R.id.radio_female) as RadioButton).setOnClickListener(this)

        return v
    }

    override fun onClick(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.radio_big_pet,
                R.id.radio_medium_pet,
                R.id.radio_small_pet,
                R.id.radio_mini_pet ->
                    if (checked) {
                        viewModel.setPetSize(view.text.toString())
                    }
                R.id.radio_male,
                R.id.radio_female ->
                    if (checked) {
                        viewModel.setPetSex(view.text.toString())
                    }
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        viewModel.setPetType(petType.selectedItem.toString())
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}


    override fun onStart() {
        super.onStart()

        nextButton.setOnClickListener {
            if (viewModel.validateStep1()) {
                val action = PetLostDirections.actionPetLostToPetLost2()
                v.findNavController().navigate(action)
            } else {
                Snackbar.make(rootLayout, "* Campos obligatorios", Snackbar.LENGTH_SHORT).show()
            }
        }
    }


}