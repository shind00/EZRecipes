package com.shind.ezrecipes.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.shind.ezrecipes.R
import com.shind.ezrecipes.activities.MealActivity
import com.shind.ezrecipes.adapters.MostPopularAdapter
import com.shind.ezrecipes.databinding.FragmentHomeBinding
import com.shind.ezrecipes.pojo.CategoryMeals
import com.shind.ezrecipes.pojo.Meal
import com.shind.ezrecipes.pojo.MealList
import com.shind.ezrecipes.retrofit.RetrofitInstance
import com.shind.ezrecipes.viewmodel.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel
    private lateinit var randomMeal: Meal
    private lateinit var popularItemsAdapter: MostPopularAdapter

    companion object{
        const val MEAL_ID = "com.shind.ezrecipes.fragments.idMeal"
        const val MEAL_NAME = "com.shind.ezrecipes.fragments.nameMeal"
        const val MEAL_THUMB = "com.shind.ezrecipes.fragments.thumbMeal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProvider(this)[HomeViewModel::class.java]
        popularItemsAdapter = MostPopularAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparePopularItemsRecyclerView()
        homeMvvm.getRandomMeal()
        observerRandomMeal()
        onRandomMealClick()
        homeMvvm.getPopularItems()
        observePopularItemsLiveData()
        onPopularItemClick()
    }

    private fun onPopularItemClick() {
        popularItemsAdapter.onItemClick = { meal ->
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID, meal.idMeal)
            intent.putExtra(MEAL_NAME, meal.strMeal)
            intent.putExtra(MEAL_THUMB, meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun preparePopularItemsRecyclerView() {
        binding.recViewMealsPopular.apply{
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularItemsAdapter
        }
    }

    private fun observePopularItemsLiveData() {
        homeMvvm.oberservePopularItemsLiveData().observe(viewLifecycleOwner) { mealList ->
            popularItemsAdapter.setMeals(mealsList = mealList as ArrayList<CategoryMeals>)
        }
    }

    private fun onRandomMealClick() {
        binding.randomMealCard.setOnClickListener {
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    /*
    Old Way
    Recreating function
     */
    /*private fun observerRandomMeal() {
        homeMvvm.observeRandomMealLivedata().observe(viewLifecycleOwner, object: Observer<Meal>{
            override fun onChanged(t: Meal?) {
                Glide.with(this@HomeFragment)
                    .load(t!!.strMealThumb)
                    .into(binding.imgRandomMeal)
            }
        })
    }*/
    private fun observerRandomMeal() {
        homeMvvm.observeRandomMealLivedata().observe(viewLifecycleOwner) { meal ->
            Glide.with(this@HomeFragment)
                .load(meal!!.strMealThumb)
                .into(binding.imgRandomMeal)
            this.randomMeal = meal
        }
    }

}