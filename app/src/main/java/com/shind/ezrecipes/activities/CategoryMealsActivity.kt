package com.shind.ezrecipes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.shind.ezrecipes.R
import com.shind.ezrecipes.adapters.CategoryMealsAdapter
import com.shind.ezrecipes.databinding.ActivityCategoryMealsBinding
import com.shind.ezrecipes.fragments.HomeFragment
import com.shind.ezrecipes.viewmodel.CategoryMealsViewModel

class CategoryMealsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryMealsBinding
    lateinit var categoryMealsViewModel: CategoryMealsViewModel
    lateinit var categorymealsAdapter: CategoryMealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareReclcyerView()

        categoryMealsViewModel = ViewModelProvider(this)[CategoryMealsViewModel::class.java]

        categoryMealsViewModel.getMealsByFactory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)

        categoryMealsViewModel.observeMealsLiveData().observe(this){ mealsList ->
            binding.tvCategoryCount.text = mealsList.size.toString()
            categorymealsAdapter.setMealsList(mealsList)
        }
    }

    private fun prepareReclcyerView() {
        categorymealsAdapter = CategoryMealsAdapter()
        binding.rvMeals.apply{
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = categorymealsAdapter
        }
    }
}