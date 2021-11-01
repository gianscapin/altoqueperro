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
    lateinit var petImage: ImageView
    lateinit var petName: TextView
    lateinit var petType: TextView
    lateinit var petSize: TextView
    lateinit var petSex: TextView
    lateinit var petCoat: TextView
    lateinit var petEyeColor: TextView
    lateinit var confirmButton: Button
    lateinit var cancelButton: Button
    lateinit var requestScore: RequestScore
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
        lostPetRequest = SimilarPetFragmentArgs.fromBundle(requireArguments()).petRequest as LostPetRequest //ToDo desp se envia directo un LostPetRequest

        val foundPetRequest: PetRequest = requestScore.request!!

        petName.text = requestScore.score.toString()
        petType.text = foundPetRequest.pet.type
        petSize.text = foundPetRequest.pet.size
        petSex.text = foundPetRequest.pet.sex
        petCoat.text = foundPetRequest.pet.coat
        petEyeColor.text = foundPetRequest.pet.eyeColor


        Glide.with(view.context).load(R.drawable.atp_logo).into(petImage)

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
           // lostPetRequest.nextStateCancel(foundPetRequest)
            backToSimilarPetList()
        }

    }

    fun backToMenu(){
        val action = SimilarPetFragmentDirections.actionSimilarPetFragmentToNewMapModeFragment()
        v.findNavController().navigate(action);
    }

    fun backToSimilarPetList(){
        v.findNavController().popBackStack()
    }

    /*private fun saveAndBackToMenu() {
        //if (lostPetData.requestConsumer != null)viewModel.updateRequests().saveFoundPetRequest(similarPetData)
        saveChanges()
        backToMenu()
    }*/

    private fun goToUserData(){
        val action = SimilarPetFragmentDirections.actionSimilarPetFragmentToSimilarPetConfirm(
            requestScore.request!!.requestCreator)
        v.findNavController().navigate(action);
    }

    private fun saveChanges(){
        viewModel.updateRequests(requestScore.request,lostPetRequest)
    }

}