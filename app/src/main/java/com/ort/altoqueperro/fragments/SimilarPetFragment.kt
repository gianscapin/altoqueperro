package com.ort.altoqueperro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.LostPetRequest
import com.ort.altoqueperro.entities.PetRequest
import com.ort.altoqueperro.entities.RequestScore
import com.ort.altoqueperro.viewmodels.SimilarPetViewModel

class SimilarPetFragment : Fragment() {
    lateinit var v: View
    private lateinit var petImage: ImageView
    lateinit var petName: TextView
    lateinit var petType: TextView
    lateinit var petSize: TextView
    private lateinit var petSex: TextView
    private lateinit var petCoat: TextView
    private lateinit var petEyeColor: TextView
    private lateinit var confirmButton: Button
    private lateinit var cancelButton: Button
    private lateinit var requestScore: RequestScore
    lateinit var lostPetRequest: LostPetRequest

    companion object {
        fun newInstance() = SimilarPetFragment()
    }

    private lateinit var viewModel: SimilarPetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.similar_pet_fragment, container, false)
        petImage = v.findViewById(R.id.imageSimilarPetDetail)
        petName = v.findViewById(R.id.txtSimilarPetName)
        petType = v.findViewById(R.id.txtSimilarPetType)
        petSize = v.findViewById(R.id.txtSimilarPetSize)
        petSex = v.findViewById(R.id.txtSimilarPetSex)
        petCoat = v.findViewById(R.id.txtSimilarPetCoat)
        petEyeColor = v.findViewById(R.id.txtSimilarPetEyeColor)
        confirmButton = v.findViewById(R.id.btnSimilarPetConfirm)
        cancelButton = v.findViewById(R.id.btnSimilarPetCancel)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SimilarPetViewModel::class.java)
        requestScore = SimilarPetFragmentArgs.fromBundle(requireArguments()).requestScore
        lostPetRequest = SimilarPetFragmentArgs.fromBundle(requireArguments()).petRequest

        val foundPetRequest: PetRequest = requestScore.request!!

        petName.text = requestScore.score.toString()
        petType.text = foundPetRequest.pet.type
        petSize.text = foundPetRequest.pet.size
        petSex.text = foundPetRequest.pet.sex
        petCoat.text = foundPetRequest.pet.furColor
        petEyeColor.text = foundPetRequest.pet.eyes


        Glide.with(view.context).load(foundPetRequest.imageURL).into(petImage)

        if (!lostPetRequest.isOpen()) {
            cancelButton.visibility = View.GONE
            confirmButton.visibility = View.GONE
        }

        confirmButton.setOnClickListener {
            lostPetRequest.nextStateConfirm(foundPetRequest)
            saveChanges()
            goToUserData()
        }

        cancelButton.setOnClickListener {
            backToSimilarPetList()
        }

    }


    private fun backToSimilarPetList() {
        v.findNavController().popBackStack()
    }

    private fun goToUserData() {
        val action = SimilarPetFragmentDirections.actionSimilarPetFragmentToSimilarPetConfirm(
            requestScore.request!!.requestCreator
        )
        v.findNavController().navigate(action)
    }

    private fun saveChanges() {
        viewModel.updateRequests(requestScore.request, lostPetRequest)
    }

}