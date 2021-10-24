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
import com.ort.altoqueperro.entities.Shelter

class ShelterListAdapter(private var shelterList : MutableList<Shelter>,
                         val onShelterClick: (Shelter) -> Unit
) : RecyclerView.Adapter <ShelterListAdapter.ShelterHolder>() {
    class ShelterHolder(v: View): RecyclerView.ViewHolder(v) {
    private var view: View = v

    fun setName(title: String){
        val txt: TextView = view.findViewById(R.id.txtShelterItemName)
        txt.text = title
    }

    fun setPicture(url: String){
        val image: ImageView = view.findViewById(R.id.imageShelterItem)
        Glide.with(view.context).load(url).into(image)
    }

    fun getCardLayout (): CardView {
        return view.findViewById(R.id.cardItem)
    }

}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShelterHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.shelter_item, parent, false)
    return (ShelterHolder(view))
}

override fun onBindViewHolder(holder: ShelterHolder, position: Int) {
    val shelter = shelterList[position]
    holder.setName(shelter.name)
    holder.setPicture(shelter.imageUrl)
    holder.getCardLayout().setOnClickListener {
        onShelterClick(shelter)
    }
}

override fun getItemCount(): Int {
    return shelterList.size
}
}