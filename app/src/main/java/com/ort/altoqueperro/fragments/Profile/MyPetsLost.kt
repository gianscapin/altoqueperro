package com.ort.altoqueperro.fragments.Profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ort.altoqueperro.R
import com.ort.altoqueperro.viewmodels.MyPetsLostViewModel

class MyPetsLost : Fragment() {

    companion object {
        fun newInstance() = MyPetsLost()
    }

    private lateinit var viewModel: MyPetsLostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_pets_lost_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyPetsLostViewModel::class.java)
        // TODO: Use the ViewModel
    }

}