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

class PetAdapter(private var requestList: MutableList<LostPetRequest>,
                 val onPetClick: (PetRequest) -> Unit
) : RecyclerView.Adapter <PetAdapter.PetHolder>() {
    class PetHolder(v: View): RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun setName(title: String){
            val txt: TextView = view.findViewById(R.id.txtPetFoundItemScore)
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pet_item, parent, false)
        return (PetHolder(view))
    }

    override fun onBindViewHolder(holder: PetHolder, position: Int) {
        val petRequest = requestList[position]
        val pet = petRequest.pet
        pet.name?.let { holder.setName(it) }
        holder.setDistance(ServiceLocation.getDistance(petRequest.coordinates).roundToInt().toString()+" mts.")
        //holder.setState(pet.state)*/
        holder.getCardLayout().setOnClickListener {
            onPetClick(petRequest)
        }
    }

    override fun getItemCount(): Int {
        return requestList.size
    }
}