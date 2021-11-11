package com.ort.altoqueperro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.LocationServices
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.FoundPetRequest
import com.ort.altoqueperro.entities.LostPetRequest
import com.ort.altoqueperro.entities.PetRequest
import com.ort.altoqueperro.utils.ServiceLocation
import kotlin.math.roundToInt

class MyFoundPetAdapter(private var requestList: MutableList<FoundPetRequest>,
                        val onPetClick: (PetRequest) -> Unit
) : RecyclerView.Adapter <MyFoundPetAdapter.PetHolder>() {
    class PetHolder(v: View): RecyclerView.ViewHolder(v) {
        private var view: View = v

        /*
        fun setName(title: String){
            val txt: TextView = view.findViewById(R.id.txtPetFoundItemScore)
            txt.text = title
        }

         */

        fun setName(title: String){
            val txt: TextView = view.findViewById(R.id.namePetItem)
            txt.text = title
        }


        fun setSize(title: String){
            val txt: TextView = view.findViewById(R.id.sizePetItem)
            txt.text = title
        }

        fun setSex(title: String){
            val txt: TextView = view.findViewById(R.id.sexPetItem)
            txt.text = title
        }

        fun setEyes(title: String){
            val txt: TextView = view.findViewById(R.id.eyesPetItem)
            txt.text = title
        }

        fun setNose(title: String){
            val txt: TextView = view.findViewById(R.id.nosePetItem)
            txt.text = title
        }

        fun setColor(title: String){
            val txt: TextView = view.findViewById(R.id.colorPetItem)
            txt.text = title
        }

        fun setDateLost(title: String){
            val txt: TextView = view.findViewById(R.id.dateLostPetItem)
            txt.text = title
        }


        fun setDistance(distance: String){
            val txt: TextView = view.findViewById(R.id.txtPetFoundItemDesc)
            txt.text = distance
        }

        /*fun setPicture(url: String){
            val image: ImageView = view.findViewById(R.id.imagePetItem)
            Glide.with(view.context).load(url).into(image)
        }*/

        fun getCardLayout (): CardView {
            return view.findViewById(R.id.cardItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_pet_item, parent, false)
        return (PetHolder(view))
    }

    override fun onBindViewHolder(holder: PetHolder, position: Int) {
        val petRequest = requestList[position]
        val pet = petRequest.pet
        pet.name?.let { holder.setName(it) }

        pet.size?.let { holder.setSize(it) }
        pet.sex?.let { holder.setSex(it) }
        pet.eyes?.let {
            if(it.equals("")){
                holder.setEyes("-")
            }else{
                holder.setEyes(it)
            }
        }
        pet.nose?.let {
            if(it.equals("")){
                holder.setNose("-")
            }else{
                holder.setNose(it)
            }
        }

        pet.furLength?.let {
            if(it.equals("")){
                holder.setColor("-")
            }else{
                holder.setColor(it)
            }
        }

        pet.lostDate?.let {
            if(it.equals("")){
                holder.setDateLost("-")
            }else{
                holder.setDateLost(it)
            }
        }
        holder.setDistance(ServiceLocation.getDistance(petRequest.coordinates).roundToInt().toString()+" mts.")
        holder.getCardLayout().setOnClickListener {
            onPetClick(petRequest)
        }
    }

    override fun getItemCount(): Int {
        return requestList.size
    }
}