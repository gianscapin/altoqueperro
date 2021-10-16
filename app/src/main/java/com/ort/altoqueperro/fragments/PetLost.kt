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
    /*lateinit var petLat: TextView
    lateinit var petLong:TextView*/
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
        petType = v.findViewById(R.id.petLostType)
        petSize = v.findViewById(R.id.petLostSize)
        petSex = v.findViewById(R.id.petLostSex)
        petCoat = v.findViewById(R.id.petLostCoat)
        petEyeColor = v.findViewById(R.id.petLostEyeColor)
        sendPet = v.findViewById(R.id.btnSendLostPet)
        /*petLat = v.findViewById(R.id.petLostLat)
        petLong = v.findViewById(R.id.petLostLong)*/

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
            /*var lat = petLat.text.toString()
            var long = petLong.text.toString()*/

            database = Firebase.database.reference

            if(name.isNotEmpty() && type.isNotEmpty() && size.isNotEmpty() && sex.isNotEmpty() && coat.isNotEmpty() && eyeColor.isNotEmpty()){
                registerPet(name, type, size, sex, coat, eyeColor)
                lookForSimilarities()
            }
        }
    }

    fun registerPet(name:String, type:String, size:String, sex:String, coat:String, eyeColor:String):Unit{

        val data = hashMapOf(
            "name" to name,
            "type" to type,
            "size" to size,
            "sex" to sex,
            "coat" to coat,
            "eyeColor" to eyeColor,
            /*"lat" to lat,
            "long" to long*/
        )

        db.collection("petsLost").document().set(data)
    }

    fun lookForSimilarities(){
        var action = PetLostDirections.actionPetLostToPetLostSearchSimilarities()
        v.findNavController().navigate(action);
    }

}