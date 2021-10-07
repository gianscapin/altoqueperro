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
import com.ort.altoqueperro.adapter.ShelterListAdapter
import com.ort.altoqueperro.entities.Shelter
import com.ort.altoqueperro.repos.ShelterRepository
import com.ort.altoqueperro.viewmodels.ShelterListViewModel

class ShelterListFragment : Fragment() {
    lateinit var v: View
    private lateinit var recShelters : RecyclerView
    var shelterRepository : ShelterRepository = ShelterRepository()

    companion object {
        fun newInstance() = ShelterListFragment()
    }

    private lateinit var listViewModel: ShelterListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v =  inflater.inflate(R.layout.shelter_list_fragment, container, false)
        recShelters = v.findViewById(R.id.recylcer_view_shelterList)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listViewModel = ViewModelProvider(this).get(ShelterListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        recShelters.setHasFixedSize(true)
        recShelters.layoutManager = LinearLayoutManager(context)
        recShelters.adapter = ShelterListAdapter(shelterRepository.shelters){ onShelterClick(it)}
    }

    fun onShelterClick(shelter : Shelter){
        //Snackbar.make(v, shelter.name.toString(),Snackbar.LENGTH_SHORT).show()
         val action =
            ListModeDirections.actionListModeToShelterItemFragment(shelter)
         v.findNavController().navigate(action);
    }

}