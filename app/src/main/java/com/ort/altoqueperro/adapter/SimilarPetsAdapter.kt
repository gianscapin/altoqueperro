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

class SimilarPetsAdapter(private var similarPetList : MutableList<Pet>,
                         val onSimilarPetsClick: (Pet) -> Unit
) : RecyclerView.Adapter <SimilarPetsAdapter.SimilarPetsHolder>() {
    class SimilarPetsHolder(v: View): RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun setName(title: String){
            val txt: TextView = view.findViewById(R.id.txtPetItemName)
            txt.text = title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarPetsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pet_item, parent, false)
        return (SimilarPetsHolder(view))
    }

    override fun onBindViewHolder(holder: SimilarPetsHolder, position: Int) {
        val pet = similarPetList[position]
        holder.setName(pet.name)
        /*holder.setPicture(pet.pictureUrl)
        holder.setState(pet.state)*/
        /*holder.getCardLayout().setOnClickListener {
            onPetClick(pet)
        }*/
    }

    override fun getItemCount(): Int {
        return similarPetList.size
    }
}