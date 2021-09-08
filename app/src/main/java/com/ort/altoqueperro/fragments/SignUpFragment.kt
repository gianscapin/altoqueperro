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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.User
import com.ort.altoqueperro.viewmodels.SignUpViewModel
import org.w3c.dom.Text

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    lateinit var userName: TextView
    lateinit var userPassword: TextView
    lateinit var userMail: TextView
    lateinit var passwordVerify: TextView
    lateinit var signUp: Button
    lateinit var v: View
    val db= Firebase.firestore

    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.sign_up_fragment, container, false)
        userName = v.findViewById(R.id.userName)
        userPassword = v.findViewById(R.id.userPassword)
        userMail = v.findViewById(R.id.userMail)
        passwordVerify = v.findViewById(R.id.passwordAuth)
        signUp = v.findViewById(R.id.btnSignUser)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        signUp.setOnClickListener {
            var nameUser = userName.text.toString()
            var passwordUser = userPassword.text.toString()
            var mailUser = userMail.text.toString()

            var user : User = User(nameUser,mailUser,passwordUser)
            println(userName.text)

            if(mailUser.isNotEmpty() && passwordUser.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(mailUser,passwordUser)
            }
            //db.collection("users").add(user)
        }
    }

}