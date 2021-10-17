package com.ort.altoqueperro.viewmodels

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.ort.altoqueperro.entities.*
import com.ort.altoqueperro.repos.PetRepository
import java.util.*

class NewMapModeViewModel : ViewModel() {
    fun getPetRequests() : MutableList<PetRequest> {
        var pets = PetRepository().getLostPets()
        var requests = mutableListOf<PetRequest>()
        var i = 0
        pets.forEach { requests.add(LostPetRequest(
                it,
                State.PENDING,
                Date(),
                null,
                LatLng(-34.609062 ,-58.427683+(i*0.001)),
                User("Prueba","nada",""),
                User("Prueba","nada",""),
            ))
            i++
        }

        return requests
    }
}