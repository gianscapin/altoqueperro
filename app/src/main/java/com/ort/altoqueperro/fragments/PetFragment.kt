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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.R
import com.ort.altoqueperro.viewmodels.PetViewModel

class PetFragment : Fragment() {

    companion object {
        fun newInstance() = PetFragment()
    }

    lateinit var welcomeText: TextView
    lateinit var v: View
    lateinit var foundPet: Button
    lateinit var lostPet: Button

    private lateinit var viewModel: PetViewModel
    //private lateinit var database: DatabaseReference
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.pet_fragment, container, false)

        welcomeText = v.findViewById(R.id.newMapModeText)
        foundPet = v.findViewById(R.id.foundPetBtn)
        lostPet = v.findViewById(R.id.lostPetBtn)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PetViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        welcomeMessage()

        foundPet.setOnClickListener {
            foundPet()
        }

        lostPet.setOnClickListener {
            lostPet()
        }
    }

    fun welcomeMessage():Unit{
        val user = Firebase.auth.currentUser

        val docRef = db.collection("users").document(user?.uid.toString())
        docRef.get().addOnSuccessListener {
            welcomeText.text = "Bienvenido "+it.get("name").toString()+"!"
        }

    }

    fun foundPet():Unit{
        val action = PetFragmentDirections.actionPetFragment2ToPetFound()
        v.findNavController().navigate(action)
    }

    fun lostPet():Unit{
        val action = PetFragmentDirections.actionPetFragment2ToPetLost()
        v.findNavController().navigate(action)
    }

}