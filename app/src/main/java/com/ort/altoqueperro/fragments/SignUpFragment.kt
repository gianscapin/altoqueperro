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
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
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
    lateinit var userPhone: TextView
    lateinit var userBirth: TextView
    lateinit var signUp: Button
    lateinit var v: View
    val db= Firebase.firestore

    private lateinit var viewModel: SignUpViewModel
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.sign_up_fragment, container, false)
        userName = v.findViewById(R.id.userName)
        userPassword = v.findViewById(R.id.userPassword)
        userMail = v.findViewById(R.id.userMail)
        userPhone = v.findViewById(R.id.userPhone)
        userBirth = v.findViewById(R.id.userBirth)
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
            var phoneUser = userPhone.text.toString()
            var birthUser = userBirth.text.toString()

            database = Firebase.database.reference

            if(nameUser.isNotEmpty() && passwordUser.isNotEmpty() && mailUser.isNotEmpty() && phoneUser.isNotEmpty() && birthUser.isNotEmpty()){
                registerUser(mailUser,passwordUser, nameUser, phoneUser, birthUser)
            }else{
                Snackbar.make(v,"Tenes que completar todos los campos",Snackbar.LENGTH_SHORT).show()
            }

            //db.collection("users").add(user)
        }
    }

    fun registerUser(mail:String, password:String, name:String, phone:String, birth:String): Unit{
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail,password).addOnCompleteListener{ task ->
            if(task.isSuccessful){

                val id = FirebaseAuth.getInstance().currentUser?.uid.toString()

                val data = hashMapOf(
                    "name" to name,
                    "email" to mail,
                    "phone" to phone,
                    "birth" to birth,
                    "id" to id
                )


                //database.child("users").child(id).setValue(data)
                db.collection("users").document(id).set(data)
                val action = SignUpFragmentDirections.actionSignUpFragmentToPetFragment()
                v.findNavController().navigate(action)
            }
        }
    }

}