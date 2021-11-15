package com.ort.altoqueperro.fragments

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.util.Linkify
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentFactory
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.R
import com.ort.altoqueperro.activities.MainActivity
import com.ort.altoqueperro.activities.SplashActivity
import com.ort.altoqueperro.viewmodels.NewProfileUserViewModel
import org.w3c.dom.Text
import java.util.*

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
    ): View? {
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewProfileUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onStart() {
        super.onStart()

        loadInfo()

        nameUser.inputType = 0
        phoneUser.inputType = 0
        birthUser.inputType = 0

        btnEdit.isVisible = false

        nameUser.setOnClickListener {
            nameUser.inputType = 1
            btnEdit.isVisible = true
            if(btnEdit.isEnabled){
                btnEdit.text = "Confirmar"
            }
        }


        phoneUser.setOnClickListener {
            phoneUser.inputType = 1
            btnEdit.isVisible = true
            if(btnEdit.isEnabled){
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
                DatePickerDialog.OnDateSetListener { view: DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
                    val mMonthFix = mMonth+1
                    birthUser.text = "$mDay/$mMonthFix/$mYear"
                },
                year,
                month,
                day
            )
            dpd.show()
        }




        btnChangePassword.setOnClickListener {
            val action = NewProfileUserFragmentDirections.actionNewProfileUserFragmentToChangePassword(user!!)
            v.findNavController().navigate(action)
        }

        btnEdit.setOnClickListener {

            val docRef = db.collection("users").document(user?.uid.toString())

            docRef.update("name",nameUser.text.toString())
            docRef.update("phone",phoneUser.text.toString())
            docRef.update("birth",birthUser.text.toString())

            btnEdit.text = "Editar"
            btnEdit.isVisible = false

            nameUser.inputType = 0
            phoneUser.inputType = 0
            birthUser.inputType = 0

            //Snackbar.make(v,"Perfil actualizado!", Snackbar.LENGTH_SHORT).
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

    fun loadInfo():Unit{

        val docRef = db.collection("users").document(user?.uid.toString())

        docRef.get().addOnSuccessListener {
            name = it.get("name").toString()
            phone = it.get("phone").toString()
            birth = it.get("birth").toString()

            nameUser.text = name
            phoneUser.text = phone
            birthUser.text = birth

            if(it.get("image")!=null){
                println(it.get("image"))
                print("imagen usuario")
                Glide.with(view?.context).load(it.get("image")).into(userImage)
            }else{
                print("imagen app")
                Glide.with(view?.context).load(R.drawable.dog_loupe).into(userImage)
            }


        }

    }

}