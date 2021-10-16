package com.ort.altoqueperro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.Pet

class PetAdapter(private var petList : MutableList<Pet>,
                         val onPetClick: (Pet) -> Unit
) : RecyclerView.Adapter <PetAdapter.PetHolder>() {
    class PetHolder(v: View): RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun setName(title: String){
            val txt: TextView = view.findViewById(R.id.txtPetItemName)
            txt.text = title
        }

        /*fun setType(type: String){
            val txt: TextView = view.findViewById(R.id.txtPetItemState)
            txt.text = state
        }

        fun setState(state: String){
            val txt: TextView = view.findViewById(R.id.txtPetItemState)
            txt.text = state
        }

        fun setState(state: String){
            val txt: TextView = view.findViewById(R.id.txtPetItemState)
            txt.text = state
        }

        fun setState(state: String){
            val txt: TextView = view.findViewById(R.id.txtPetItemState)
            txt.text = state
        }

        fun setState(state: String){
            val txt: TextView = view.findViewById(R.id.txtPetItemState)
            txt.text = state
        }*/

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
        val pet = petList[position]
        holder.setName(pet.name)
        /*holder.setPicture(pet.pictureUrl)
        holder.setState(pet.state)*/
        holder.getCardLayout().setOnClickListener {
            onPetClick(pet)
        }
    }

    override fun getItemCount(): Int {
        return petList.size
    }
}