package com.ort.altoqueperro.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ort.altoqueperro.R
import com.ort.altoqueperro.viewmodels.NewProfileUserViewModel

class NewProfileUserFragment : Fragment() {

    companion object {
        fun newInstance() = NewProfileUserFragment()
    }

    private lateinit var viewModel: NewProfileUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_profile_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewProfileUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}