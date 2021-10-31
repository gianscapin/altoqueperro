package com.ort.altoqueperro.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.R
import com.ort.altoqueperro.viewmodels.NewProfileUserViewModel
import org.w3c.dom.Text

class NewProfileUserFragment : Fragment() {

    companion object {
        fun newInstance() = NewProfileUserFragment()
    }

    lateinit var nameUser: TextView
    lateinit var phoneUser: TextView
    lateinit var birthUser: TextView
    lateinit var btnChangePassword:TextView
    lateinit var v:View

    val db = Firebase.firestore
    val user = Firebase.auth.currentUser

    private lateinit var name: String
    private lateinit var phone: String
    private lateinit var birth: String

    private lateinit var viewModel: NewProfileUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.new_profile_user_fragment, container, false)

        nameUser = v.findViewById(R.id.nameUser)
        phoneUser = v.findViewById(R.id.phoneUser)
        birthUser = v.findViewById(R.id.birthUser)
        btnChangePassword = v.findViewById(R.id.btnChangePassword)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewProfileUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        loadInfo()

        btnChangePassword.setOnClickListener {
            val action = NewProfileUserFragmentDirections.actionNewProfileUserFragmentToChangePassword(user!!)
            v.findNavController().navigate(action)
        }
    }

    fun loadInfo():Unit{

        val docRef = db.collection("users").document(user?.uid.toString())

        docRef.get().addOnSuccessListener {
            name = it.get("name").toString()
            phone = it.get("phone").toString()
            birth = it.get("birth").toString()

            nameUser.text = name
            phoneUser.text = phone
            birthUser.text = birth

            //passwordUser = it.get("password").toString()
        }

    }

}