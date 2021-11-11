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
import com.ort.altoqueperro.utils.ServiceLocation
import kotlin.math.roundToInt

class VetListAdapter(private var vetList : MutableList<Vet>,
                     val onVetClick: (Vet) -> Unit
) : RecyclerView.Adapter <VetListAdapter.VetHolder>() {
    class VetHolder(v: View): RecyclerView.ViewHolder(v) {
        var view: View = v
        var neighborhood:TextView = view.findViewById(R.id.neighborhood)
        var distancia: TextView = view.findViewById(R.id.distancia)
        var txtname: TextView = view.findViewById(R.id.txtVetName)
        var address: TextView = view.findViewById(R.id.txtVetAddress)
        var phone: TextView = view.findViewById(R.id.txtVetPhoneNumber)
        var businesshour: TextView = view.findViewById(R.id.txtVetBusinessHours)
        var logo: ImageView = view.findViewById(R.id.txtVetLogo)

        fun setData (vet:Vet) {
           // neighborhood.text = vet.neighborhood
            var distance = 0
            if(vet.coordinates != null){
                distance = ServiceLocation.getDistance(vet.coordinates!!).roundToInt()
            }
            distancia.text = distance.toString()+" mts."
            txtname.text = vet.name
            address.text = vet.address
            phone.text = vet.phone
            businesshour.text = vet.businessHours
            Glide.with(view.context).load(vet.imageUrl).into(logo)
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