package com.ort.altoqueperro.utils

import android.location.Location
import com.google.android.gms.maps.model.LatLng
import com.ort.altoqueperro.fragments.NewMapModeFragment
object LocationService {
    init {
        println("Singleton class invoked.")
    }
    fun getLocation(): LatLng? {
        if (!NewMapModeFragment.currentLocation.provider.equals("null")) {
            return LatLng(
                NewMapModeFragment.currentLocation.latitude,
                NewMapModeFragment.currentLocation.longitude
            )
        }
        return null
    }
    fun getDistance(to:LatLng):Float{
        //retorna distancia en metros
        val from = Location("")
        var latlng = getLocation()
        if (latlng == null) return 0f
        from.setLatitude(latlng.latitude)
        from.setLongitude(latlng.longitude)

        val loc_to = Location("")
        loc_to.setLatitude(to.latitude)
        loc_to.setLongitude(to.longitude)
        return from.distanceTo(loc_to)
    }
}