package com.ort.altoqueperro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.Coordinates
import com.ort.altoqueperro.entities.LostPetRequest
import com.ort.altoqueperro.entities.Pet
import com.ort.altoqueperro.utils.ServiceLocation
import com.ort.altoqueperro.viewmodels.PetLostViewModel

class PetLost : Fragment() {

    companion object {
        fun newInstance() = PetLost()
    }

    lateinit var petName: TextView
    lateinit var petType: TextView
    lateinit var petSize: TextView
    lateinit var petSex: TextView
    lateinit var petCoat: TextView
    lateinit var petEyeColor: TextView
    lateinit var sendPet: Button
    lateinit var v: View

    private lateinit var viewModel: PetLostViewModel

    val db = Firebase.firestore
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.pet_lost_fragment, container, false)
        petName = v.findViewById(R.id.petLostName)
        petType = v.findViewById(R.id.petLostType)
        petSize = v.findViewById(R.id.petLostSize)
        petSex = v.findViewById(R.id.petLostSex)
        petCoat = v.findViewById(R.id.petLostCoat)
        petEyeColor = v.findViewById(R.id.petLostEyeColor)
        sendPet = v.findViewById(R.id.btnSendLostPet)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PetLostViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        sendPet.setOnClickListener {
            var name = petName.text.toString()
            var type = petType.text.toString()
            var size = petSize.text.toString()
            var sex = petSex.text.toString()
            var coat = petCoat.text.toString()
            var eyeColor = petEyeColor.text.toString()

            database = Firebase.database.reference
            if (ServiceLocation.location.provider.equals("null")) {
                Toast.makeText(
                    activity,
                    "No hemos podido determinar su ubicación",
                    Toast.LENGTH_LONG
                )
            } else {
                if (name.isNotEmpty() && type.isNotEmpty() && size.isNotEmpty() && sex.isNotEmpty() && coat.isNotEmpty() && eyeColor.isNotEmpty()) {
                    registerPet(
                        name,
                        type,
                        size,
                        sex,
                        coat,
                        eyeColor,
                        ServiceLocation.getLocation()
                    )
                }
            }
        }
    }

    fun registerPet( //ToDo va en el repository
        name: String,
        type: String,
        size: String,
        sex: String,
        coat: String,
        eyeColor: String,
        location: Coordinates?
    ): Unit {
        val user = Firebase.auth.currentUser
        val pet = Pet(name, type, size, sex, coat, eyeColor)

        println(location)
        val petRequest = LostPetRequest(pet, location, user!!.uid)
        db.collection("lostPetRequests").document().set(petRequest)

        var action = PetLostDirections.actionPetLostToPetLostSearchSimilarities(petRequest)
        v.findNavController().navigate(action);
    }


}