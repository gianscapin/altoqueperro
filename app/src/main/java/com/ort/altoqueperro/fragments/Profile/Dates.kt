package com.ort.altoqueperro.fragments.Profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ort.altoqueperro.R
import com.ort.altoqueperro.viewmodels.DatesViewModel

class Dates : Fragment() {

    companion object {
        fun newInstance() = Dates()
    }

    lateinit var nameText: TextView
    lateinit var mailText: TextView
    lateinit var phoneText: TextView
    lateinit var birthText: TextView
    lateinit var v:View

    private lateinit var viewModel: DatesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.dates_fragment, container, false)
        nameText = v.findViewById(R.id.nameUser)
        mailText = v.findViewById(R.id.mailUser)
        phoneText = v.findViewById(R.id.nameUser)
        birthText = v.findViewById(R.id.birthUser)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DatesViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        var name = DatesArgs.fromBundle(requireArguments()).name
        var mail = DatesArgs.fromBundle(requireArguments()).mail
        var phone = DatesArgs.fromBundle(requireArguments()).phone
        var birth = DatesArgs.fromBundle(requireArguments()).birth

        completeDates(name,mail,phone,birth)


    }

    fun completeDates(name:String,mail:String,phone:String,birth:String):Unit{
        nameText.text = name
        mailText.text = mail
        phoneText.text = phone
        birthText.text = birth
    }



}