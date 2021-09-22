package com.ort.altoqueperro.fragments.Profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.R
import com.ort.altoqueperro.fragments.ProfileViewModel

class Profile : Fragment() {

    companion object {
        fun newInstance() = Profile()
    }

    private lateinit var nameUser:String
    private lateinit var mailUser:String
    private lateinit var phoneUser: String
    private lateinit var birthUser: String

    lateinit var btnDates: Button
    lateinit var btnPetsLost: Button
    lateinit var btnSettings: Button
    lateinit var v:View
    val db = Firebase.firestore

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.profile_fragment, container, false)
        btnDates = v.findViewById(R.id.btnDates)
        btnPetsLost = v.findViewById(R.id.btnPetsLost)
        btnSettings = v.findViewById(R.id.btnSettings)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        loadInfo()
    }

    fun loadInfo():Unit{

        val user = Firebase.auth.currentUser


        val docRef = db.collection("users").document(user?.uid.toString())

        docRef.get().addOnSuccessListener {
            nameUser = it.get("name").toString()
            mailUser = it.get("email").toString()
            phoneUser = it.get("phone").toString()
            birthUser = it.get("birth").toString()
        }

        btnDates.setOnClickListener {
            val action = ProfileDirections.actionProfileUserToDates(nameUser,mailUser,birthUser,phoneUser)
            v.findNavController().navigate(action)
        }

        btnPetsLost.setOnClickListener {
            val action = ProfileDirections.actionProfileUserToMyPetsLost()
            v.findNavController().navigate(action)
        }

        btnSettings.setOnClickListener {
            val action = ProfileDirections.actionProfileUserToSettings()
            v.findNavController().navigate(action)
        }
    }


}