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
import com.google.android.gms.maps.model.LatLng
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.Vet
import com.ort.altoqueperro.utils.ServiceLocation
import com.ort.altoqueperro.viewmodels.ShelterItemViewModel
import kotlin.math.roundToInt

class VetItemFragment : Fragment() {
    lateinit var v: View
    lateinit var neighborhood: TextView
    lateinit var distancia: TextView
    lateinit var txtname: TextView
    lateinit var address: TextView
    lateinit var phone: TextView
    lateinit var businesshour: TextView
    lateinit var logo: ImageView

    companion object {
        fun newInstance() = VetItemFragment()
    }

    private lateinit var viewModel: ShelterItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.vet_item, container, false)
        neighborhood = v.findViewById(R.id.neighborhood)
        distancia = v.findViewById(R.id.distancia)
        txtname = v.findViewById(R.id.txtname)
        address = v.findViewById(R.id.address)
        phone = v.findViewById(R.id.phone)
        businesshour = v.findViewById(R.id.businesshour)
        logo = v.findViewById(R.id.logo)
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
        //neighborhood.text = vetData.neighborhood
        distancia.text = distance.toString()+" mts."
        txtname.text = vetData.name
        //address.text = vetData.street + " " + vetData.streetNumber
        phone.text = vetData.phone
        businesshour.text = vetData.businessHours
        Glide.with(view.context).load(vetData.imageUrl).into(logo)

    }

}