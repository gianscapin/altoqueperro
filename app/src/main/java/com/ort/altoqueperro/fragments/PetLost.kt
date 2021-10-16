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
import com.ort.altoqueperro.entities.Pet
import com.ort.altoqueperro.viewmodels.PetLostViewModel
import org.w3c.dom.Text

class PetLost : Fragment() {

    companion object {
        fun newInstance() = PetLost()
    }

    lateinit var petName: TextView
    lateinit var petBreed: TextView
    lateinit var petColor: TextView
    lateinit var petLat: TextView
    lateinit var petLong:TextView
    lateinit var sendPet: Button
    lateinit var v: View

    private lateinit var viewModel: PetLostViewModel

    val db = Firebase.firestore
    private lateinit var database:DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.pet_lost_fragment, container, false)
        petName = v.findViewById(R.id.petLostName)
        petBreed = v.findViewById(R.id.petLostBreed)
        petColor = v.findViewById(R.id.petLostColor)
        petLat = v.findViewById(R.id.petLostLat)
        petLong = v.findViewById(R.id.petLostLong)
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
            var breed = petBreed.text.toString()
            var color = petColor.text.toString()
            var lat = petLat.text.toString()
            var long = petLong.text.toString()

            database = Firebase.database.reference

            if(name.isNotEmpty() && breed.isNotEmpty() && color.isNotEmpty() && lat.isNotEmpty() && long.isNotEmpty()){
                registerPet(name,breed,color,lat,long)
                lookForSimilarities()
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

    fun lookForSimilarities(){
        var action = PetLostDirections.actionPetLostToPetLostSearchSimilarities()
        v.findNavController().navigate(action);
    }

}