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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.FoundPetRequest
import com.ort.altoqueperro.entities.Pet
import com.ort.altoqueperro.viewmodels.PetFoundViewModel

class PetFound : Fragment() {

    companion object {
        fun newInstance() = PetFound()
    }

    lateinit var petType: TextView
    lateinit var petSize: TextView
    lateinit var petSex: TextView
    lateinit var petCoat: TextView
    lateinit var petEyeColor: TextView
    lateinit var sendPet: Button
    lateinit var v: View

    private lateinit var viewModel: PetFoundViewModel

    val db = Firebase.firestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.pet_found_fragment, container, false)
        petType = v.findViewById(R.id.petFoundType)
        petSize = v.findViewById(R.id.petFoundSize)
        petSex = v.findViewById(R.id.petFoundSex)
        petCoat = v.findViewById(R.id.petFoundCoat)
        petEyeColor = v.findViewById(R.id.petFoundEyeColor)
        sendPet = v.findViewById(R.id.btnSendFoundPet)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PetFoundViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        sendPet.setOnClickListener {
            var type = petType.text.toString()
            var size = petSize.text.toString()
            var sex = petSex.text.toString()
            var coat = petCoat.text.toString()
            var eyeColor = petEyeColor.text.toString()


            if (type.isNotEmpty() && size.isNotEmpty() && sex.isNotEmpty() && coat.isNotEmpty() && eyeColor.isNotEmpty()) {
                registerPet(type, size, sex, coat, eyeColor)
            }
        }
    }

    fun registerPet(
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
    }


}