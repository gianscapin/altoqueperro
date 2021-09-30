package com.ort.altoqueperro.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ort.altoqueperro.R
import com.ort.altoqueperro.databinding.ActivityHomeNavigationBinding
import com.ort.altoqueperro.fragments.PetFragment
import com.ort.altoqueperro.fragments.PetLost

class HomeNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView4) as NavHostFragment
        val navController = navHostFragment.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.newMapModeFragment,R.id.shelterItemFragment,R.id.newProfileUserFragment, R.id.settings)
        )

        val floatingButtonAction:View = findViewById(R.id.btnFloatingActions)
        val lostPetBtn:View = findViewById(R.id.fab1)
        val foundPetBtn:View = findViewById(R.id.fab2)

        floatingButtonAction.setOnClickListener {
            if(lostPetBtn.isInvisible){
                lostPetBtn.isInvisible=false
                foundPetBtn.isInvisible=false
            }else{
                lostPetBtn.isInvisible=true
                foundPetBtn.isInvisible=true
            }
        }

        lostPetBtn.setOnClickListener {
            lostPetBtn.isInvisible=true
            foundPetBtn.isInvisible=true
            navController.navigate(R.id.petLost)
        }

        foundPetBtn.setOnClickListener {
            lostPetBtn.isInvisible=true
            foundPetBtn.isInvisible=true
            navController.navigate(R.id.petFound)
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}