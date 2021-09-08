package com.ort.altoqueperro.fragments

import android.content.ContentValues.TAG
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.FirebaseCommonKtxRegistrar
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.User
import com.ort.altoqueperro.viewmodels.LogViewModel


class LogFragment : Fragment() {



    companion object {
        fun newInstance() = LogFragment()
    }

    lateinit var inputMail: TextView
    lateinit var inputPassword: TextView
    lateinit var login: Button
    lateinit var signUp: Button
    lateinit var rootLayout:ConstraintLayout
    lateinit var v:View
    val db = Firebase.firestore

    var usersList : MutableList<User> = arrayListOf()

    private lateinit var viewModel: LogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.log_fragment, container, false)

        login = v.findViewById(R.id.btnLogIn)
        signUp = v.findViewById(R.id.btnSign)
        inputMail = v.findViewById(R.id.idUser)
        inputPassword = v.findViewById(R.id.idPassword)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LogViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        signUp.setOnClickListener {
            println("ingresando")
            val action = LogFragmentDirections.actionLogFragmentToSignUpFragment2()
            v.findNavController().navigate(action)
        }

        login.setOnClickListener {
            var mail = inputMail.text.toString()
            var password = inputPassword.text.toString()



            /*
            val usersRef = db.collection("users")


            val query = usersRef.whereEqualTo("email",mail).whereEqualTo("password",password).get().addOnSuccessListener { snapshot ->
                if(snapshot != null){
                    for(user in snapshot){
                        usersList.add(user.toObject())
                    }
                }
            }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }

            if(usersList.size>0){
                println(usersList[0].toString())
            }
             */

            if(mail.isNotEmpty() && password.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(mail,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        println("inicio de sesión")
                    }else{
                        println("credenciales no válidas")
                    }
                }
            }


        }
    }

}