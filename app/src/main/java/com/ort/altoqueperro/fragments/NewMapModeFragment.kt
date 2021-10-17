package com.ort.altoqueperro.fragments
import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.location.LocationServices
import com.ort.altoqueperro.R
import com.ort.altoqueperro.entities.PetRequest
import com.ort.altoqueperro.utils.PermissionUtils.isPermissionGranted
import com.ort.altoqueperro.utils.PermissionUtils.requestPermission
import com.ort.altoqueperro.viewmodels.NewMapModeViewModel


class NewMapModeFragment : Fragment(), GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener, OnMapReadyCallback,
    ActivityCompat.OnRequestPermissionsResultCallback {

    private lateinit var map:GoogleMap
    private var permissionDenied = false
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var currentLocation: Location

    companion object {
        fun newInstance() = NewMapModeFragment()
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var viewModel: NewMapModeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v:View = inflater.inflate(R.layout.new_map_mode_fragment, container, false)
        var mapFragment: SupportMapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.requireActivity())
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewMapModeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap ?: return
        googleMap.setOnMyLocationButtonClickListener(this)
        googleMap.setOnMyLocationClickListener(this)
        enableMyLocation()
        loadMarkers(viewModel.getPetRequests())
    }
    fun loadMarkers(pets:MutableList<PetRequest>) {
        //map.clear()
        pets.forEach {
            var marker: MarkerOptions = MarkerOptions().position(it.coordinates).title(it.pet.name)
            map.addMarker(marker)
        }
    }

    private fun gotoMyLocation() {
        if (ActivityCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this.requireContext(),"Permiso de acceso denegado",Toast.LENGTH_LONG)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-34.6090638,-58.4289158), 17.0f))
            return
        }
        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null) {
                currentLocation = location
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(currentLocation.latitude,currentLocation.longitude), 17.0f))
            }
        }
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private fun enableMyLocation() {
        if (!::map.isInitialized) return
        // [START maps_check_location_permission]
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
        } else {
            // Permission to access the location is missing. Show rationale and request permission
            requestPermission(
                this.activity as AppCompatActivity, LOCATION_PERMISSION_REQUEST_CODE,
                Manifest.permission.ACCESS_FINE_LOCATION, true
            )
        }
        gotoMyLocation()
    }
    private fun createMarker() {
        map.clear()
        var coordinates = LatLng(-34.6209926,-58.426453)
        var marker:MarkerOptions = MarkerOptions().position(coordinates).title("La casa de Maia")
        map.addMarker(marker)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 17.0f))
    }

    override fun onMyLocationButtonClick(): Boolean {
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false
    }

    override fun onMyLocationClick(location: Location) {
    }
    // [START maps_check_location_permission_result]
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return
        }
        if (isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation()
        } else {
            // Permission was denied. Display an error message
            // [START_EXCLUDE]
            // Display the missing permission error dialog when the fragments resume.
            Toast.makeText(requireContext(),"No permitio que sepamos donde esta asi que el mapa aparecera por cualquier lado",Toast.LENGTH_LONG)
            permissionDenied = true
            // [END_EXCLUDE]
        }
    }
}