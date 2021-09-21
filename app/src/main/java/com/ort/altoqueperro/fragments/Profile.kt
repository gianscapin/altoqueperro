package com.ort.altoqueperro.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.R

class Profile : Fragment() {

    companion object {
        fun newInstance() = Profile()
    }

    lateinit var nameText: TextView
    lateinit var mailText: TextView
    lateinit var phoneText: TextView
    lateinit var birthText: TextView
    lateinit var v:View
    val db = Firebase.firestore

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.profile_fragment, container, false)
        nameText = v.findViewById(R.id.nameUser)
        mailText = v.findViewById(R.id.mailUser)
        phoneText = v.findViewById(R.id.phoneUser)
        birthText = v.findViewById(R.id.birthUser)

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
            nameText.text = it.get("name").toString()
            mailText.text = it.get("email").toString()
            phoneText.text = it.get("phone").toString()
            birthText.text = it.get("birth").toString()
        }
    }


}