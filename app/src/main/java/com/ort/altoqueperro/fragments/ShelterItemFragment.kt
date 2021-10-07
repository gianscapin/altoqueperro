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
import com.ort.altoqueperro.entities.Shelter
import com.ort.altoqueperro.viewmodels.ShelterItemViewModel

class ShelterItemFragment : Fragment() {
    lateinit var v: View
    lateinit var shelterName: TextView
    lateinit var shelterAddress: TextView
    lateinit var shelterPhoneNumber: TextView
    lateinit var shelterDescription: TextView
    lateinit var shelterImage: ImageView

    companion object {
        fun newInstance() = ShelterItemFragment()
    }

    private lateinit var viewModel: ShelterItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.shelter_item_fragment, container, false)
        shelterName = v.findViewById(R.id.txtShelterName)
        shelterAddress = v.findViewById(R.id.txtShelterAddress)
        shelterDescription = v.findViewById(R.id.txtShelterDesc)
        shelterPhoneNumber = v.findViewById(R.id.txtShelterPhoneNumber)
        shelterImage = v.findViewById(R.id.imageShelterDetail)
        return  v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShelterItemViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val shelterData = ShelterItemFragmentArgs.fromBundle(requireArguments()).shelterData

        shelterName.text = shelterData.name
        shelterAddress.text = shelterData.address.getFormattedAddress()
        shelterPhoneNumber.text = shelterData.phoneNumber
        shelterDescription.text = shelterData.description
        Glide.with(view.context).load(shelterData.logoUrl).into(shelterImage)

    }

}