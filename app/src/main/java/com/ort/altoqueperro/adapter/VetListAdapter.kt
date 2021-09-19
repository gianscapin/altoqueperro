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
import com.ort.altoqueperro.entities.Vet

class VetListAdapter(private var vetList : MutableList<Vet>,
                     val onVetClick: (Vet) -> Unit
) : RecyclerView.Adapter <VetListAdapter.VetHolder>() {
    class VetHolder(v: View): RecyclerView.ViewHolder(v) {
        var view: View = v
        var neighborhood:TextView = view.findViewById(R.id.neighborhood)
        var distancia: TextView = view.findViewById(R.id.neighborhood)
        var txtname: TextView = view.findViewById(R.id.neighborhood)
        var address: TextView = view.findViewById(R.id.neighborhood)
        var phone: TextView = view.findViewById(R.id.neighborhood)
        var businesshour: TextView = view.findViewById(R.id.neighborhood)
        var logo: ImageView = view.findViewById(R.id.neighborhood)

        fun setData (vet:Vet) {
            neighborhood.text = vet.neighborhood
            distancia.text = "100 mts."
            txtname.text = vet.name
            address.text = vet.street + " " + vet.streetNumber
            phone.text = vet.phone
            businesshour.text = vet.businessHours
            Glide.with(view.context).load(vet.logoURL).into(logo)
        }
    fun getCardLayout (): CardView {
        return view.findViewById(R.id.cardItem)
    }
}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VetHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.vet_item, parent, false)
    return (VetHolder(view))
}

override fun onBindViewHolder(holder: VetHolder, position: Int) {
    val vet = vetList[position]
    holder.setData(vet)
    holder.getCardLayout().setOnClickListener {
        onVetClick(vet)
    }
}

override fun getItemCount(): Int {
    return vetList.size
}
}