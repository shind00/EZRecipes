package com.shind.ezrecipes.retrofit

import com.shind.ezrecipes.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {
    @GET("random.php")
    fun getRandomMeal(): Call<MealList>
}