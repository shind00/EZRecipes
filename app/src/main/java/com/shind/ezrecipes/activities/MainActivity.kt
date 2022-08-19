package com.shind.ezrecipes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.shind.ezrecipes.R
import com.shind.ezrecipes.databinding.ActivityMainBinding
import com.shind.ezrecipes.db.MealDatabase
import com.shind.ezrecipes.viewmodel.HomeViewModel
import com.shind.ezrecipes.viewmodel.HomeViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel: HomeViewModel by lazy {
        val mealDatabase = MealDatabase.getInstance(this)
        val homeViewModelProviderFactory = HomeViewModelFactory(mealDatabase)
        ViewModelProvider(this, homeViewModelProviderFactory)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.host_fragment)

        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }
}