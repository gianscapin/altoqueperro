package com.ort.altoqueperro.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import com.ort.altoqueperro.R
import com.ort.altoqueperro.utils.Notifications
import com.ort.altoqueperro.viewmodels.SettingsViewModel

class SettingsFragment : Fragment() {

    lateinit var switchPetFound: Switch
    lateinit var switchPetLost: Switch
    lateinit var switchNotification: Switch
    lateinit var v: View

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.settings_fragment, container, false)
        switchPetFound = v.findViewById(R.id.switchPetFound)
        switchPetLost = v.findViewById(R.id.switchPetLost)
        switchNotification = v.findViewById(R.id.switchNotifications)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        switchPetLost.isChecked = Notifications.getNotificationPetLost()
        switchPetFound.isChecked = Notifications.getNotificationPetFound()

        if(switchPetLost.isChecked && switchPetFound.isChecked){
            switchNotification.isChecked = true
        }else{
            switchNotification.isChecked = false
        }


        switchPetLost.setOnClickListener{
            /*
            if(switchPetLost.isChecked){
                Notifications.setNotificationPetLost(switchPetLost.isChecked)
            }

             */

            refreshSwitchPetLost()

        }

        switchPetFound.setOnClickListener{
            /*
            if(switchPetFound.isChecked){
                Notifications.setNotificationPetFound(switchPetFound.isChecked)
            }

             */
            refreshSwitchPetFound()
        }

        switchNotification.setOnClickListener {
            switchPetLost.isChecked = switchNotification.isChecked
            switchPetFound.isChecked = switchNotification.isChecked
            refreshSwitchPetLost()
            refreshSwitchPetFound()
        }

    }

    fun refreshSwitchPetLost():Unit{
        Notifications.setNotificationPetLost(switchPetLost.isChecked)
    }

    fun refreshSwitchPetFound():Unit{
        Notifications.setNotificationPetFound(switchPetFound.isChecked)
    }

}