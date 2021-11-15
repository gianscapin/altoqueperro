package com.ort.altoqueperro.fragments

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.R
import com.ort.altoqueperro.activities.MainActivity
import com.ort.altoqueperro.viewmodels.NewProfileUserViewModel

class NewProfileUserFragment : Fragment() {

    companion object {
        fun newInstance() = NewProfileUserFragment()
    }


    lateinit var nameUser: TextView
    lateinit var phoneUser: TextView
    lateinit var birthUser: TextView
    lateinit var btnChangePassword:TextView
    lateinit var btnEdit: Button
    lateinit var userImage: ImageView
    lateinit var v:View
    lateinit var btnLogout : TextView
    lateinit var auth : FirebaseAuth

    val db = Firebase.firestore
    val user = Firebase.auth.currentUser


    private lateinit var name: String
    private lateinit var phone: String
    private lateinit var birth: String

    private lateinit var viewModel: NewProfileUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.new_profile_user_fragment, container, false)

        nameUser = v.findViewById(R.id.nameUser)
        phoneUser = v.findViewById(R.id.phoneUser)
        birthUser = v.findViewById(R.id.birthUser)
        btnEdit = v.findViewById(R.id.btnEdit)
        userImage = v.findViewById(R.id.userImage)
        btnChangePassword = v.findViewById(R.id.btnChangePassword)
        btnLogout = v.findViewById(R.id.btnLogout)
        auth = Firebase.auth

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewProfileUserViewModel::class.java)
        viewModel.userLiveData.observe(viewLifecycleOwner, {
            nameUser.text = it.name
            phoneUser.text = it.phone
            birthUser.text = it.birth
            if (fbUser?.photoUrl != null) {
                print("imagen usuario")
                Glide.with(v.context).load(fbUser.photoUrl).into(userImage)
            } else {
                print("imagen app")
                Glide.with(v.context).load(R.drawable.dog_loupe).into(userImage)
            }
        })
        viewModel.success.observe(viewLifecycleOwner, {
            //Snackbar.make(v,"Perfil actualizado!", Snackbar.LENGTH_SHORT).
            val toast = Toast.makeText(context, "Perfil actualizado!", Toast.LENGTH_LONG)
            toast.show()
        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onStart() {
        super.onStart()

        viewModel.getUser(fbUser?.uid.toString())
        clearFields()

        nameUser.setOnClickListener {
            nameUser.inputType = 1
            btnEdit.isVisible = true
            if (btnEdit.isEnabled) {
                btnEdit.text = "Confirmar"
            }
        }


        phoneUser.setOnClickListener {
            phoneUser.inputType = 1
            btnEdit.isVisible = true
            if (btnEdit.isEnabled) {
                btnEdit.text = "Confirmar"
            }
        }

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        birthUser.setOnClickListener {
            val dpd = DatePickerDialog(
                requireContext(),
                { _: DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
                    val mMonthFix = mMonth + 1
                    birthUser.text = "$mDay/$mMonthFix/$mYear"
                },
                year,
                month,
                day
            )
            dpd.show()
        }


        btnChangePassword.setOnClickListener {
            val action =
                NewProfileUserFragmentDirections.actionNewProfileUserFragmentToChangePassword(fbUser!!)
            v.findNavController().navigate(action)
        }

        btnEdit.setOnClickListener {
            viewModel.editUser(
                nameUser.text.toString(),
                phoneUser.text.toString(),
                birthUser.text.toString()
            )
            clearFields()

            val docRef = db.collection("users").document(user?.uid.toString())

            docRef.update("name",nameUser.text.toString())
            docRef.update("phone",phoneUser.text.toString())
            docRef.update("birth",birthUser.text.toString())

            btnEdit.text = "Editar"
            btnEdit.isVisible = false

            nameUser.inputType = 0
            phoneUser.inputType = 0
            birthUser.inputType = 0

            val toast = Toast.makeText(context,"Perfil actualizado!", Toast.LENGTH_LONG)
            toast.show()

        }

        btnLogout.setOnClickListener {
            auth.signOut()
            val logoutIntent = Intent(v.context, MainActivity::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logoutIntent)

        }

    }

    private fun clearFields() {

        nameUser.inputType = 0
        phoneUser.inputType = 0
        birthUser.inputType = 0

        btnEdit.isVisible = false

    }

}