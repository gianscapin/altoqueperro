package com.ort.altoqueperro.viewmodels

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.ort.altoqueperro.entities.*
import com.ort.altoqueperro.repos.PetRepository
import com.ort.altoqueperro.repos.RequestRepository
import java.util.*

class NewMapModeViewModel : ViewModel() {
    fun getPetRequests() : MutableList<FoundPetRequest> {
        var requests = RequestRepository().foundRequests
        var i = 0
        requests.forEach {
            it.coordinates = LatLng(-34.609062 ,-58.427683+(i*0.001))
            i++
        }

        return requests
    }
}