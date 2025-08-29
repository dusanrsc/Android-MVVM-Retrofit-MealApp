package com.infinitysoftware.mvvm_retrofit_mealapp.network

import com.infinitysoftware.mvvm_retrofit_mealapp.model.MealResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val baseUrl: String = "https://www.themealdb.com/api/json/v1/1/"
private val retrofit = Retrofit
    .Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val mealService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET(value = "categories.php")
    suspend fun getMeal(): MealResponse
}