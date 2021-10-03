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
import com.ort.altoqueperro.viewmodels.ProfileViewModel

class Profile : Fragment() {

    companion object {
        fun newInstance() = Profile()
    }

    lateinit var nameUser:TextView
    lateinit var mailUser:TextView
    lateinit var phoneUser: TextView
    lateinit var birthUser: TextView
    lateinit var v:View

    val db = Firebase.firestore

    private lateinit var name: String
    private lateinit var mail: String
    private lateinit var phone: String
    private lateinit var birth: String

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.profile_fragment, container, false)

        nameUser = v.findViewById(R.id.nameUser)
        mailUser = v.findViewById(R.id.mailUser)
        phoneUser = v.findViewById(R.id.phoneUser)
        birthUser = v.findViewById(R.id.birthUser)

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
            name = it.get("name").toString()
            mail = it.get("email").toString()
            phone = it.get("phone").toString()
            birth = it.get("birth").toString()

            nameUser.text = name
            mailUser.text = mail
            phoneUser.text = phone
            birthUser.text = birth
        }

    }


}