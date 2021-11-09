package com.ort.altoqueperro.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ort.altoqueperro.R
import com.ort.altoqueperro.viewmodels.PetFoundViewModel

class PetFound3 : Fragment() {

    companion object {
        fun newInstance() = PetFound3()
    }

    lateinit var nextButton : Button
    lateinit var date : TextView
    lateinit var time : TextView
    lateinit var comments : TextView
    lateinit var v : View
    lateinit var rootLayout : ConstraintLayout
    private val viewModel: PetFoundViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.pet_found_3_fragment, container, false)

        nextButton = v.findViewById(R.id.btnNext3)

        rootLayout = v.findViewById(R.id.pet_found_root_layout_3)

        comments = v.findViewById(R.id.txtComments)
        comments.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setComments(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        date = v.findViewById(R.id.txtDate)
        date.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setLostDate(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        time = v.findViewById(R.id.txtTime)
        time.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setTime(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        return v
    }

    override fun onStart() {
        super.onStart()

        nextButton.setOnClickListener {
            if (viewModel.validateStep3()) {
                val action = PetFound3Directions.actionPetFound3ToPetFoundConfirmation()
                v.findNavController().navigate(action)
            }
            else {
                Snackbar.make(rootLayout, "* Campos obligatorios", Snackbar.LENGTH_SHORT).show()

            }
        }
    }
}