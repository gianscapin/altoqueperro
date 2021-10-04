package com.ort.altoqueperro.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.R
import com.ort.altoqueperro.viewmodels.PetFoundViewModel

class PetFound : Fragment() {

    companion object {
        fun newInstance() = PetFound()
    }

    lateinit var petName: TextView
    lateinit var petBreed: TextView
    lateinit var petColor: TextView
    lateinit var petLat: TextView
    lateinit var petLong: TextView
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
        petName = v.findViewById(R.id.petFoundName)
        petBreed = v.findViewById(R.id.petFoundBreed)
        petColor = v.findViewById(R.id.petFoundColor)
        petLat = v.findViewById(R.id.petFoundLat)
        petLong = v.findViewById(R.id.petFoundLong)
        sendPet = v.findViewById(R.id.btnSendFoundPet)

        return v    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PetFoundViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        sendPet.setOnClickListener {
            var name = petName.text.toString()
            var breed = petBreed.text.toString()
            var color = petColor.text.toString()
            var lat = petLat.text.toString()
            var long = petLong.text.toString()

            database = Firebase.database.reference

            if (name.isNotEmpty() && breed.isNotEmpty() && color.isNotEmpty() && lat.isNotEmpty() && long.isNotEmpty()) {
                registerPet(name, breed, color, lat, long)
            }
        }
    }
    
    fun registerPet(name:String,breed:String,color:String,lat:String,long:String):Unit{

        val data = hashMapOf(
            "name" to name,
            "breed" to breed,
            "color" to color,
            "lat" to lat,
            "long" to long
        )

        db.collection("petsLost").document().set(data)
    }


}