package com.ort.altoqueperro.fragments

import android.content.ContentValues.TAG
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
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

    private lateinit var viewModel: PetViewModel
    //private lateinit var database: DatabaseReference
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.pet_fragment, container, false)

        welcomeText = v.findViewById(R.id.welcomeText)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PetViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        mensajeDeBienvenida()
    }

    fun mensajeDeBienvenida():Unit{
        val user = Firebase.auth.currentUser

        val docRef = db.collection("users").document(user?.uid.toString())
        docRef.get().addOnSuccessListener {
            welcomeText.text = "Bienvenido "+it.get("name").toString()+"!"
        }

    }

}