package com.ort.altoqueperro.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.altoqueperro.R
import com.ort.altoqueperro.adapter.ShelterListAdapter
import com.ort.altoqueperro.adapter.VetListAdapter
import com.ort.altoqueperro.entities.Shelter
import com.ort.altoqueperro.entities.ShelterRepository
import com.ort.altoqueperro.entities.Vet
import com.ort.altoqueperro.entities.VetRepository
import com.ort.altoqueperro.viewmodels.ShelterListViewModel
import com.ort.altoqueperro.viewmodels.VetListViewModel

class VetListFragment : Fragment() {
    lateinit var v: View
    private lateinit var recVet : RecyclerView
    var vetRepository : VetRepository = VetRepository()

    companion object {
        fun newInstance() = VetListFragment()
    }

    private lateinit var listViewModel: VetListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v =  inflater.inflate(R.layout.vet_list_fragment, container, false)
        recVet = v.findViewById(R.id.recylcer_view_vetList)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listViewModel = ViewModelProvider(this).get(VetListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        recVet.setHasFixedSize(true)
        recVet.layoutManager = LinearLayoutManager(context)
        recVet.adapter = VetListAdapter(vetRepository.getAllVets()){ onVetClick(it)}
    }

    fun onVetClick(vet : Vet){
         val action =
            VetListFragmentDirections.actionVetListFragmentToVetItemFragment(vet)
         v.findNavController().navigate(action);
    }

}