package com.ort.altoqueperro.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.Vet
import com.ort.altoqueperro.utils.ServiceLocation
import com.ort.altoqueperro.viewmodels.ShelterItemViewModel
import kotlin.math.roundToInt

class VetItemFragment : Fragment() {
    lateinit var v: View
    lateinit var neighborhood: TextView
    lateinit var distancia: TextView
    lateinit var name: TextView
    lateinit var phoneNumber: TextView
    lateinit var businessHours: TextView
    lateinit var imageLogo: ImageView
    lateinit var address : TextView

    companion object {
        fun newInstance() = VetItemFragment()
    }

    private lateinit var viewModel: ShelterItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.vet_item_fragment, container, false)
        address = v.findViewById(R.id.textDirection)
        name = v.findViewById(R.id.txtVetName)
        phoneNumber = v.findViewById(R.id.txtVetPhoneNumber)
        businessHours = v.findViewById(R.id.txtVetBusinessHours)
        imageLogo = v.findViewById(R.id.txtVetLogo)
        neighborhood = v.findViewById(R.id.txtVetLocalidad)
        distancia = v.findViewById(R.id.txtDistance)
        return  v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShelterItemViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vetData:Vet = VetItemFragmentArgs.fromBundle(requireArguments()).vetData

        println(vetData.coordinates?.latitude.toString()+" "+vetData.coordinates?.longitude.toString())
        var distance = 0
        if(vetData.coordinates != null){
            distance = ServiceLocation.getDistance(vetData.coordinates!!).roundToInt()
        }
        distancia.text = "$distance mts."
        address.text = vetData.address
        name.text = vetData.name
        neighborhood.text = vetData.localidad
        phoneNumber.text = vetData.phone
        businessHours.text = "Horacio de Atención: " + vetData.businessHours
        Glide.with(view.context).load(vetData.imageUrl).into(imageLogo)

    }

}