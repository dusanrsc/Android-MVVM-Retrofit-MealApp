package com.infinitysoftware.mvvm_retrofit_mealapp.model

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)

data class MealResponse(
    val categories: List<Category>
)
