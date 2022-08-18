package com.shind.ezrecipes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.shind.ezrecipes.R
import com.shind.ezrecipes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.host_fragment)

        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }
}