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
import com.ort.altoqueperro.viewmodels.ProfileViewModel

class Profile : Fragment() {

    companion object {
        fun newInstance() = Profile()
    }

    lateinit var nameUser:TextView
    lateinit var phoneUser: TextView
    lateinit var birthUser: TextView
    lateinit var btnPassword: Button
    lateinit var v:View
    val db = Firebase.firestore
    val user = Firebase.auth.currentUser
    private lateinit var passwordUser:String

    private lateinit var name: String
    private lateinit var phone: String
    private lateinit var birth: String

    private lateinit var viewModel: ProfileViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.profile_fragment, container, false)

        nameUser = v.findViewById(R.id.nameUser)
        phoneUser = v.findViewById(R.id.phoneUser)
        birthUser = v.findViewById(R.id.birthUser)
        btnPassword = v.findViewById(R.id.btnChangePassword)


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

        btnPassword.setOnClickListener {
        }
    }

    fun loadInfo(): Unit {


        val docRef = db.collection("users").document(user?.uid.toString())

        docRef.get().addOnSuccessListener {
            name = it.get("name").toString()
            phone = it.get("phone").toString()
            birth = it.get("birth").toString()

            nameUser.text = name
            phoneUser.text = phone
            birthUser.text = birth

            passwordUser= it.get("password").toString()

        }
    }


}