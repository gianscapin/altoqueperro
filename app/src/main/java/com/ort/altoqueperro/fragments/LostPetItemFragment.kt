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
    lateinit var lostPetName: TextView
    lateinit var lostPetState: TextView
    lateinit var lostPetImage: ImageView

    companion object {
        fun newInstance() = LostPetItemFragment()
    }

    private lateinit var viewModel: LostPetItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.pet_item_fragment, container, false)
        lostPetName = v.findViewById(R.id.txtPetName)
        lostPetState = v.findViewById(R.id.txtPetState)
        lostPetImage = v.findViewById(R.id.imagePetDetail)

        return  v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LostPetItemViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lostPetData = LostPetItemFragmentArgs.fromBundle(requireArguments()).petData
        val lostPetScore = LostPetItemFragmentArgs.fromBundle(requireArguments()).petScore

        if (lostPetData != null){
            lostPetName.text = lostPetData.pet.name
           // lostPetState.text = lostPetData.requestCreator.toString()

        }else if (lostPetScore != null){
            lostPetName.text = lostPetScore.score.toString()
            //lostPetState.text = lostPetScore.request?.requestCreator.toString()

        }

        Glide.with(view.context).load(R.drawable.atp_logo).into(lostPetImage)

    }

}