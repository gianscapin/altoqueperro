package com.ort.altoqueperro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.Pet
import com.ort.altoqueperro.entities.PetScore

class SimilarPetsAdapter(private var similarPetList : MutableList<PetScore>,
                         val onSimilarPetsClick: (PetScore) -> Unit
) : RecyclerView.Adapter <SimilarPetsAdapter.SimilarPetsHolder>() {
    class SimilarPetsHolder(v: View): RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun setScore(score: Int){
            val txt: TextView = view.findViewById(R.id.txtPetFoundItemScore)
            txt.text = "${score}% Match"
        }

        fun getCardLayout (): CardView {
            return view.findViewById(R.id.cardItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarPetsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pet_item_found, parent, false)
        return (SimilarPetsHolder(view))
    }

    override fun onBindViewHolder(holder: SimilarPetsHolder, position: Int) {
        val petScore = similarPetList[position]
        holder.setScore(petScore.score)
        /*holder.setPicture(pet.pictureUrl)
        holder.setState(pet.state)*/
        holder.getCardLayout().setOnClickListener {
            onSimilarPetsClick(petScore)
        }
    }

    override fun getItemCount(): Int {
        return similarPetList.size
    }
}