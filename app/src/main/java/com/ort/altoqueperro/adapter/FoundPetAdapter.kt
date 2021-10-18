package com.ort.altoqueperro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.Pet
import com.ort.altoqueperro.entities.PetRequest
import com.ort.altoqueperro.entities.PetScore

class FoundPetAdapter(private var petScoreList : MutableList<PetScore>,
                      val onPetClick: (PetScore) -> Unit
) : RecyclerView.Adapter <FoundPetAdapter.PetHolder>() {
    class PetHolder(v: View): RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun setScore(score: Int) {
            val txt: TextView = view.findViewById(R.id.txtPetFoundItemScore)
            txt.setText(score)
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pet_item_found, parent, false)
        return (PetHolder(view))
    }

    override fun onBindViewHolder(holder: PetHolder, position: Int) {
        val petScore = petScoreList[position]
        holder.setScore(petScore.score)
        /*holder.setPicture(pet.pictureUrl)
        holder.setState(pet.state)*/
        holder.getCardLayout().setOnClickListener {
            onPetClick(petScore)
        }
    }

    override fun getItemCount(): Int {
        return petScoreList.size
    }
}