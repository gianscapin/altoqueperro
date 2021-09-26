package com.ort.altoqueperro.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ort.altoqueperro.R
import com.ort.altoqueperro.viewmodels.NewListModeViewModel

class NewListModeFragment : Fragment() {

    companion object {
        fun newInstance() = NewListModeFragment()
    }

    private lateinit var viewModel: NewListModeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_list_mode_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewListModeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}