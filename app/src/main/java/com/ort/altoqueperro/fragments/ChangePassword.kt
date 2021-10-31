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
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.R
import com.ort.altoqueperro.activities.SplashActivity
import com.ort.altoqueperro.activities.SplashWelcomeActivity
import com.ort.altoqueperro.viewmodels.ChangePasswordViewModel
import org.w3c.dom.Text

class ChangePassword : Fragment() {



    companion object {
        fun newInstance() = ChangePassword()
    }

    lateinit var newPassword:TextView
    lateinit var confirmPassword:TextView
    lateinit var btnConfirmPassword:Button
    lateinit var v:View

    private lateinit var viewModel: ChangePasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.change_password_fragment, container, false)

        newPassword = v.findViewById(R.id.newPassword)
        confirmPassword = v.findViewById(R.id.confirmPassword)
        btnConfirmPassword = v.findViewById(R.id.btnConfirmPassword)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChangePasswordViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        val currentUser = ChangePasswordArgs.fromBundle(requireArguments()).user

        btnConfirmPassword.setOnClickListener {

            if(newPassword.text.isNotEmpty() && confirmPassword.text.isNotEmpty()){
                if(newPassword.text.contentEquals(confirmPassword.text)){

                    println("password changed")
                    currentUser!!.updatePassword(newPassword.text.toString())
                        .addOnSuccessListener {
                            Snackbar.make(v,"Password changed!", Snackbar.LENGTH_SHORT).show()
                        }
                    Firebase.auth.signOut()
                    startActivity(Intent(context, SplashActivity::class.java))

                }else{
                    Snackbar.make(v,"Las nuevas contraseñas tienen que ser iguales", Snackbar.LENGTH_SHORT).show()
                }
            }else{
                Snackbar.make(v,"Todos los campos son obligatorios", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

}