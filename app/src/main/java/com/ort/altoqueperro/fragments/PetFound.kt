package com.ort.altoqueperro.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.firebase.auth.ktx.auth
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.FoundPetRequest
import com.ort.altoqueperro.entities.Pet
import com.ort.altoqueperro.viewmodels.PetFoundViewModel

class PetFound : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance() = PetFound()
    }

    lateinit var petType: Spinner
    lateinit var petTypeValue: String
    lateinit var petSex: RadioButton
    lateinit var petSize: RadioButton
    lateinit var petTypesSpinner: Spinner

    //lateinit var petPhoto: ImageView
    //lateinit var photoUploadPhoto: Button
    lateinit var nextButton: Button
    lateinit var v: View

    lateinit var rootLayout: ConstraintLayout

    private val viewModel: PetFoundViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.pet_found_fragment, container, false)
        nextButton = v.findViewById(R.id.btnNext)

        rootLayout = v.findViewById(R.id.pet_found_root_layout_1)

        petTypesSpinner = v.findViewById(R.id.pet_types_spinner)
        petTypesSpinner.setSelection(0, false)
        petTypesSpinner.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            v.context, R.array.pet_types, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            petTypesSpinner.adapter = adapter
        }

        (v.findViewById(R.id.radio_big_pet) as RadioButton).setOnClickListener(this)
        (v.findViewById(R.id.radio_medium_pet) as RadioButton).setOnClickListener(this)
        (v.findViewById(R.id.radio_small_pet) as RadioButton).setOnClickListener(this)
        (v.findViewById(R.id.radio_mini_pet) as RadioButton).setOnClickListener(this)

        (v.findViewById(R.id.radio_male) as RadioButton).setOnClickListener(this)
        (v.findViewById(R.id.radio_female) as RadioButton).setOnClickListener(this)
        (v.findViewById(R.id.radio_dont_know) as RadioButton).setOnClickListener(this)

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
                R.id.radio_female,
                R.id.radio_dont_know ->
                    if (checked) {
                        viewModel.setPetSex(view.text.toString())
                    }
            }
        }
    }

//    fun registerPet(type: String, size: String, sex: String, coat: String, eyeColor: String): Unit {
//        val user = Firebase.auth.currentUser
//        val pet = Pet(null, type, size, sex, coat, eyeColor)
//        val petRequest = FoundPetRequest(
//            pet,
//            State.OPEN.ordinal,
//            Calendar.getInstance().time,
//            null,
//            null,
//            user!!.uid,
//            null,
//            null
//        )
//        db.collection("foundPetRequests").document().set(petRequest)
//        var action = PetFoundDirections.actionPetFoundToPetFoundSearchSimilarities(petRequest)
//        v.findNavController().navigate(action);
//      }


        override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
            viewModel.setPetType(petTypesSpinner.selectedItem.toString())
        }

        override fun onNothingSelected(parent: AdapterView<*>) {}

    override fun onStart() {
        super.onStart()

        nextButton.setOnClickListener {
            if (viewModel.validateStep1()) {
                val action = PetFoundDirections.actionPetFoundToPetFound2()
                v.findNavController().navigate(action)
            } else {
                Snackbar.make(rootLayout, "* Campos obligatorios", Snackbar.LENGTH_SHORT).show()
            }
        }

        /*sendPet.setOnClickListener {
            var type = petType.text.toString()
            var size = petSize.text.toString()
            var sex = petSex.text.toString()
            var coat = petCoat.text.toString()
            var eyeColor = petEyeColor.text.toString()


            if (type.isNotEmpty() && size.isNotEmpty() && sex.isNotEmpty() && coat.isNotEmpty() && eyeColor.isNotEmpty()) {
                registerPet(type, size, sex, coat, eyeColor)
            }
        }*/
    }

    /*fun registerPet(
        type: String,
        size: String,
        sex: String,
        coat: String,
        eyeColor: String
    ) { //ToDo esto va en el repository
        val user = Firebase.auth.currentUser
        val pet = Pet(null, type, size, sex, coat, eyeColor)
        val petRequest = FoundPetRequest(pet, null, user!!.uid)
        db.collection("foundPetRequests").document().set(petRequest)
        var action = PetFoundDirections.actionPetFoundToPetFoundSearchSimilarities(petRequest)
        v.findNavController().navigate(action);
            nextButton.setOnClickListener {
                if (viewModel.validateStep1()) {
                    val action = PetFoundDirections.actionPetFoundToPetFound2()
                    v.findNavController().navigate(action)
                } else {
                    Snackbar.make(rootLayout, "* Campos obligatorios", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }*/



}