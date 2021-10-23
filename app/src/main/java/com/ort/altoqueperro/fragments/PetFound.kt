package com.ort.altoqueperro.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.FoundPetRequest
import com.ort.altoqueperro.entities.LostPetRequest
import com.ort.altoqueperro.entities.Pet
import com.ort.altoqueperro.entities.State
import com.ort.altoqueperro.repos.UserRepository
import com.ort.altoqueperro.viewmodels.PetFoundViewModel
import com.ort.altoqueperro.viewmodels.PetLostViewModel
import java.util.*

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
    private lateinit var database: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

            database = Firebase.database.reference

            if (type.isNotEmpty() && size.isNotEmpty() && sex.isNotEmpty() && coat.isNotEmpty() && eyeColor.isNotEmpty()) {
                registerPet(type, size, sex, coat, eyeColor)
            }
        }
    }

    fun registerPet(type:String, size:String, sex:String, coat:String, eyeColor:String):Unit{
        val user = UserRepository().getRandomUser()
        val pet = Pet(null, type, size, sex, coat, eyeColor)
        val petRequest = FoundPetRequest(pet, State.OPEN, Calendar.getInstance().time,null,null, user,null)
        db.collection("pets").document().set(pet)
        var action = PetFoundDirections.actionPetFoundToPetFoundSearchSimilarities(petRequest)
        v.findNavController().navigate(action);
    }


}