package com.ort.altoqueperro.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.R
import com.ort.altoqueperro.activities.HomeNavigationActivity
import com.ort.altoqueperro.activities.SplashWelcomeActivity
import com.ort.altoqueperro.entities.User
import com.ort.altoqueperro.viewmodels.LogViewModel


class LogFragment : Fragment() {

    companion object {
        fun newInstance() = LogFragment()
    }

    lateinit var inputMail: TextView
    lateinit var inputPassword: TextView
    lateinit var login: Button
    lateinit var signUp: TextView
    lateinit var txtSignUp: TextView
    lateinit var lostPass: TextView
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
        txtSignUp = v.findViewById(R.id.textView5)
        lostPass = v.findViewById(R.id.lostPassword)
        rootLayout = v.findViewById(R.id.frameLayout)

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
            val action = LogFragmentDirections.actionLogFragmentToSignUpFragment()
            v.findNavController().navigate(action)

        }

        lostPass.setOnClickListener {
            println("recuperar contraseña")
            val action = LogFragmentDirections.actionLogFragmentToRecoveryPasswordFragment()
            v.findNavController().navigate(action)
        }

        login.setOnClickListener {
            var mail = inputMail.text.toString()
            var password = inputPassword.text.toString()

            if(mail.isNotEmpty() && password.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(mail,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        //val action = LogFragmentDirections.actionLogFragmentToPetFragment2()
                        //v.findNavController().navigate(action)

                        startActivity(Intent(context, SplashWelcomeActivity::class.java))
                        //v.findNavController().navigate(R.id.petFragment2)
                    }else{
                        println("credenciales no válidas")
                        Snackbar.make(rootLayout, "Email o Contraseña incorrecta", Snackbar.LENGTH_LONG)
                            .show()
                    }
                }
            }else{
                Snackbar.make(rootLayout, "Email o Contraseña incorrecta", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

}