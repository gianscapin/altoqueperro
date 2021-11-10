package com.ort.altoqueperro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ort.altoqueperro.R
import com.ort.altoqueperro.viewmodels.LostPetItemViewModel

class LostPetItemFragment : Fragment() {
    lateinit var v: View
    private lateinit var lostPetImage: ImageView
    private lateinit var lostPetName: TextView
    private lateinit var lostPetType: TextView
    private lateinit var lostPetSize: TextView
    private lateinit var lostPetSex: TextView
    private lateinit var lostPetEyeColor: TextView
    private lateinit var lostPetNose: TextView
    private lateinit var lostPetFurLength: TextView
    private lateinit var lostPetFurColor: TextView
    private lateinit var lostPetComments: TextView
    private lateinit var lostPetLostDate: TextView


    companion object {
        fun newInstance() = LostPetItemFragment()
    }

    private lateinit var viewModel: LostPetItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.pet_item_fragment, container, false)
        lostPetImage = v.findViewById(R.id.imagePetDetail)
        lostPetName = v.findViewById(R.id.txtPetName)
        lostPetType = v.findViewById(R.id.txtPetType)
        lostPetSize = v.findViewById(R.id.txtPetSize)
        lostPetSex = v.findViewById(R.id.txtPetSex)
        lostPetEyeColor = v.findViewById(R.id.txtPetEyeColor)
        lostPetFurLength = v.findViewById(R.id.txtPetFurLength)
        lostPetNose = v.findViewById(R.id.txtPetNose)
        lostPetFurColor = v.findViewById(R.id.txtPetFurColor)
        lostPetComments = v.findViewById(R.id.txtPetComments)
        lostPetLostDate = v.findViewById(R.id.txtPetLostDate)

        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LostPetItemViewModel::class.java)
        val lostPetData = LostPetItemFragmentArgs.fromBundle(requireArguments()).petData
        if (lostPetData != null) {
            lostPetName.text = lostPetData.pet.name
            lostPetType.text = lostPetData.pet.type
            lostPetSize.text = lostPetData.pet.size
            lostPetSex.text = lostPetData.pet.sex
            lostPetEyeColor.text = lostPetData.pet.eyes
            lostPetFurLength.text = lostPetData.pet.furLength
            lostPetFurColor.text = lostPetData.pet.furColor
            lostPetComments.text = lostPetData.pet.comments
            lostPetLostDate.text = lostPetData.pet.lostDate

            // lostPetState.text = lostPetData.requestCreator.toString()

            Glide.with(view.context).load(R.drawable.atp_logo).into(lostPetImage)
        }


    }

}