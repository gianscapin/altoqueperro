package com.ort.altoqueperro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.altoqueperro.R
import com.ort.altoqueperro.adapter.PetAdapter
import com.ort.altoqueperro.entities.PetRequest
import com.ort.altoqueperro.viewmodels.LostPetListViewModel

class LostPetListFragment : Fragment() {
    lateinit var v: View
    private lateinit var recLostPets: RecyclerView

    companion object {
        fun newInstance() = LostPetListFragment()
    }

    private lateinit var listViewModel: LostPetListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.pet_list_fragment, container, false)
        recLostPets = v.findViewById(R.id.recylcer_view_petList)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listViewModel = ViewModelProvider(this).get(LostPetListViewModel::class.java)
        // TODO: Use the ViewModel
        listViewModel.petRepository.observe(viewLifecycleOwner, Observer {

            recLostPets.adapter = PetAdapter(listViewModel.petRepository.value!!) { onLostPetClick(it) }

        })
    }

    override fun onStart() {
        super.onStart()
        recLostPets.setHasFixedSize(true)
        recLostPets.layoutManager = LinearLayoutManager(context)
        listViewModel.getLostPets()
    }

    fun onLostPetClick(lostPet: PetRequest) {
        val action =
            ListModeDirections.actionListModeToLostPetItemFragment(lostPet, null)
        v.findNavController().navigate(action);
    }

}