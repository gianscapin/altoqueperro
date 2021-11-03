package com.ort.altoqueperro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.R
import com.ort.altoqueperro.viewmodels.PetLostViewModel

class PetLost : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance() = PetLost()
    }

    lateinit var petName: TextView
    lateinit var petType: Spinner
    lateinit var petTypeValue: String
    lateinit var petSex: RadioButton
    lateinit var petSize: RadioButton

    lateinit var nextButton: Button

    lateinit var v: View
    lateinit var rootLayout: ConstraintLayout

    private lateinit var viewModel: PetLostViewModel

    val db = Firebase.firestore
   //private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.pet_lost_1_fragment, container, false)
        nextButton = v.findViewById(R.id.btnNext)
        rootLayout = v.findViewById(R.id.pet_lost_root_layout_1)

        petName = v.findViewById(R.id.petName)

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
                val action = PetFoundDirections.actionPetFoundToPetFound2()
                v.findNavController().navigate(action)
            } else {
                Snackbar.make(rootLayout, "* Campos obligatorios", Snackbar.LENGTH_SHORT).show()
            }
        }
    }






//        nextButton.setOnClickListener {
//            var name = petName.text.toString()
//            var type = petType.text.toString()
//            var size = petSize.text.toString()
//            var sex = petSex.text.toString()
//            var coat = petCoat.text.toString()
//            var eyeColor = petEyeColor.text.toString()
//
//            database = Firebase.database.reference
//            if (ServiceLocation.location.provider.equals("null")) {
//                Toast.makeText(
//                    activity,
//                    "No hemos podido determinar su ubicación",
//                    Toast.LENGTH_LONG
//                )
//            } else {
//                if (name.isNotEmpty() && type.isNotEmpty() && size.isNotEmpty() && sex.isNotEmpty() && coat.isNotEmpty() && eyeColor.isNotEmpty()) {
//                    registerPet(
//                        name,
//                        type,
//                        size,
//                        sex,
//                        coat,
//                        eyeColor,
//                        ServiceLocation.getLocation()
//                    )
//                }
//            }
//        }
//    }
//
//    fun registerPet( //ToDo va en el repository
//        name: String,
//        type: String,
//        size: String,
//        sex: String,
//        coat: String,
//        eyeColor: String,
//        location: Coordinates?
//    ): Unit {
//        val user = Firebase.auth.currentUser
//        val pet = Pet(name, type, size, sex, coat, eyeColor)
//
//        println(location)
//        val petRequest = LostPetRequest(pet, location, user!!.uid)
//        db.collection("lostPetRequests").document().set(petRequest)
//
//        var action = PetLostDirections.actionPetLostToPetLostSearchSimilarities(petRequest)
//        v.findNavController().navigate(action);
//    }


}