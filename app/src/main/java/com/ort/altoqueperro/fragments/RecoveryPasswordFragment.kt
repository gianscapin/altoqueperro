package com.ort.altoqueperro.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.actionCodeSettings
import com.ort.altoqueperro.R
import com.ort.altoqueperro.activities.MainActivity
import com.ort.altoqueperro.viewmodels.RecoveryPasswordViewModel

class RecoveryPasswordFragment : Fragment() {

    companion object {
        fun newInstance() = RecoveryPasswordFragment()
    }

    private lateinit var viewModel: RecoveryPasswordViewModel
    private lateinit var txtEmail: EditText
    lateinit var rootLayout: ConstraintLayout
    private lateinit var auth: FirebaseAuth
    private lateinit var btnRecovery: Button
    lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.recovery_password_fragment, container, false)
        txtEmail = v.findViewById(R.id.editTextEmail)
        auth = FirebaseAuth.getInstance()
        rootLayout = v.findViewById(R.id.frameLayoutRecovery)
        btnRecovery = v.findViewById(R.id.btnRecovery)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecoveryPasswordViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        btnRecovery.setOnClickListener {
            val email = txtEmail.text.toString()
            if (email.isEmpty()) {
                Snackbar.make(rootLayout, "Por favor ingresar su Email", Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Snackbar.make(
                                rootLayout,
                                "Se envio el mail con exito",
                                Snackbar.LENGTH_LONG
                            ).show()
                            v.findNavController().navigate(RecoveryPasswordFragmentDirections.actionRecoveryPasswordFragmentToLogFragment())

                        } else {
                            Snackbar.make(
                                rootLayout,
                                "Problemas para enviar el mail de recuperación",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }
    }
}


