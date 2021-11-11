package com.ort.altoqueperro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.FoundPetRequest
import com.ort.altoqueperro.entities.LostPetRequest
import com.ort.altoqueperro.viewmodels.MyLostPetItemViewModel

class MyLostPetItemFragment : Fragment() {
    lateinit var v: View
    private lateinit var myLostPetImage: ImageView
    private lateinit var myLostPetName: TextView
    private lateinit var myLostPetType: TextView
    private lateinit var myLostPetSize: TextView
    private lateinit var myLostPetSex: TextView
    private lateinit var myLostPetCoat: TextView
    private lateinit var myLostPetEyeColor: TextView
    private lateinit var similarPetImage: ImageView
    private lateinit var similarPetType: TextView
    private lateinit var similarPetSize: TextView
    private lateinit var similarPetSex: TextView
    private lateinit var similarPetCoat: TextView
    private lateinit var similarPetEyeColor: TextView
    private lateinit var similarPetCardView: CardView
    private lateinit var lostPetData: LostPetRequest
    private var similarPetData: FoundPetRequest? = null
    private lateinit var foundButton: Button
    private lateinit var notFoundButton: Button
    private lateinit var txtConsulta : TextView
    private lateinit var btnMatch : Button
    private lateinit var btnNoMatch : Button

    companion object {
        fun newInstance() = MyLostPetItemFragment()
    }

    private lateinit var viewModel: MyLostPetItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.my_lost_pet_item_fragment, container, false)
        myLostPetImage = v.findViewById(R.id.imageMyPetDetail)
        myLostPetName = v.findViewById(R.id.txtMyPetName)
        myLostPetType = v.findViewById(R.id.txtMyPetType)
        myLostPetSize = v.findViewById(R.id.txtMyPetSize)
        myLostPetSex = v.findViewById(R.id.txtMyPetSex)
        myLostPetCoat = v.findViewById(R.id.txtMyPetCoat)
        myLostPetEyeColor = v.findViewById(R.id.txtMyPetEyeColor)
        similarPetImage = v.findViewById(R.id.imageSimilarPetDetail)
        similarPetType = v.findViewById(R.id.txtSimilarPetType)
        similarPetSize = v.findViewById(R.id.txtSimilarPetSize)
        similarPetSex = v.findViewById(R.id.txtSimilarPetSex)
        similarPetCoat = v.findViewById(R.id.txtSimilarPetCoat)
        similarPetEyeColor = v.findViewById(R.id.txtSimilarPetEyeColor)
        similarPetCardView = v.findViewById(R.id.cardItemSimilarPet)
        foundButton = v.findViewById(R.id.btnMyPetConfirm)
        notFoundButton = v.findViewById(R.id.btnMyPetCancel)
        txtConsulta = v.findViewById(R.id.textView10)
        btnMatch = v.findViewById(R.id.btnMatch)
        btnNoMatch = v.findViewById(R.id.btnNoMatch)

        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lostPetData = MyLostPetItemFragmentArgs.fromBundle(requireArguments()).petData
        viewModel = ViewModelProvider(this).get(MyLostPetItemViewModel::class.java)
        if (lostPetData.requestConsumer == null) {
            similarPetCardView.visibility = View.GONE
            txtConsulta.visibility = View.GONE
        }

        viewModel.requestConsumerLiveData.observe(viewLifecycleOwner, {
            similarPetData = it
            similarPetType.text = it.pet.type
            similarPetSize.text = it.pet.size
            similarPetSex.text = it.pet.sex
            similarPetCoat.text = it.pet.furColor
            similarPetEyeColor.text = it.pet.eyes

        })
        myLostPetName.text = lostPetData.pet.name
        myLostPetType.text = lostPetData.pet.type
        myLostPetSize.text = lostPetData.pet.size
        myLostPetSex.text = lostPetData.pet.sex
        myLostPetCoat.text = lostPetData.pet.furColor
        myLostPetEyeColor.text = lostPetData.pet.eyes
        // lostPetState.text = lostPetData.requestCreator.toString()

        Glide.with(view.context).load(R.drawable.atp_logo).into(myLostPetImage)

        if (lostPetData.isOpen()) {
            notFoundButton.text = "Reiniciar Busqueda"
            foundButton.text = "La Encontre"

            foundButton.setOnClickListener {
                lostPetData.nextStateConfirm()
                saveAndBackToMenu()
            }

            notFoundButton.setOnClickListener {
                goToNewSimilaritySearch()
            }
        } else if (lostPetData.isPending()) {

            notFoundButton.visibility = View.GONE
            foundButton.visibility = View.GONE

            btnMatch.setOnClickListener{
                lostPetData.nextStateConfirm(similarPetData!!)
                saveAndBackToMenu()
            }

            btnNoMatch.setOnClickListener {
                lostPetData.nextStateCancel(similarPetData!!)
                saveAndBackToMenu()
            }



            /*notFoundButton.text = "No era esta"
            foundButton.text = "Era esta"



            foundButton.setOnClickListener {
                lostPetData.nextStateConfirm(similarPetData!!)
                saveAndBackToMenu()
            }

            notFoundButton.setOnClickListener {
                lostPetData.nextStateCancel(similarPetData!!)
                saveAndBackToMenu()
            }*/
        }


    }

    override fun onResume() {
        super.onResume()
        viewModel.getRequestConsumer(lostPetData.requestConsumer)
    }

    private fun saveAndBackToMenu() {
        viewModel.updateRequests(similarPetData, lostPetData)
        backToMenu()
    }

    fun goToNewSimilaritySearch() {
        val action =
            MyLostPetItemFragmentDirections.actionMyLostPetItemFragmentToPetLostSearchSimilarities(
                lostPetData
            )
        v.findNavController().navigate(action);
    }

    fun backToMenu() {
        val action =
            MyLostPetItemFragmentDirections.actionMyLostPetItemFragmentToNewMapModeFragment()
        v.findNavController().navigate(action);
    }
}