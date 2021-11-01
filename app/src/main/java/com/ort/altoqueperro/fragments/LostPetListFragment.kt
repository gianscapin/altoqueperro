package com.ort.altoqueperro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.altoqueperro.R
import com.ort.altoqueperro.adapter.MyFoundPetAdapter
import com.ort.altoqueperro.adapter.MyPetAdapter
import com.ort.altoqueperro.adapter.PetAdapter
import com.ort.altoqueperro.entities.LostPetRequest
import com.ort.altoqueperro.entities.PetRequest
import com.ort.altoqueperro.viewmodels.LostPetListViewModel

class LostPetListFragment : Fragment() {
    lateinit var v: View
    private lateinit var recOwnLostPets: RecyclerView
    private lateinit var recOwnFoundPets: RecyclerView
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
        recOwnLostPets = v.findViewById(R.id.recylcer_view_ownLostPetList)
        recOwnFoundPets = v.findViewById(R.id.recylcer_view_ownFoundPetList)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listViewModel = ViewModelProvider(this).get(LostPetListViewModel::class.java)
        // TODO: Use the ViewModel
        listViewModel.petRepository.observe(viewLifecycleOwner, {
            listViewModel.distribute()
            recLostPets.adapter = PetAdapter(listViewModel.othersLostPets) { onLostPetClick(it) }
            recOwnLostPets.adapter =
                MyPetAdapter(listViewModel.myLostPets) { onMyLostPetClick(it as LostPetRequest) } //ToDo ver que todo esté usando la clase que debe

        })
        listViewModel.foundPetRepository.observe(viewLifecycleOwner, {
            recOwnFoundPets.adapter =
                MyFoundPetAdapter(listViewModel.foundPetRepository.value!!) { onLostPetClick(it) }
        })
    }

    override fun onStart() {
        super.onStart()
        recLostPets.setHasFixedSize(false)
        recLostPets.layoutManager = LinearLayoutManager(context)
        recOwnLostPets.setHasFixedSize(false)
        recOwnLostPets.layoutManager = LinearLayoutManager(context)
        recOwnFoundPets.setHasFixedSize(false)
        recOwnFoundPets.layoutManager = LinearLayoutManager(context)
        listViewModel.getLostPets()
        listViewModel.getOwnFoundPets()
    }

    fun onLostPetClick(lostPet: PetRequest) {
        val action =
            ListModeDirections.actionListModeToLostPetItemFragment(lostPet)
        v.findNavController().navigate(action)
    }

    fun onMyLostPetClick(lostPet: LostPetRequest) {
        val action =
            ListModeDirections.actionListModeToMyLostPetItemFragment(lostPet)
        v.findNavController().navigate(action)
    }

}