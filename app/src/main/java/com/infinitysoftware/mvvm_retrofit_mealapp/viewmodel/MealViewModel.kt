package com.infinitysoftware.mvvm_retrofit_mealapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infinitysoftware.mvvm_retrofit_mealapp.model.Category
import com.infinitysoftware.mvvm_retrofit_mealapp.network.mealService
import kotlinx.coroutines.launch

class MealViewModel : ViewModel() {
    private val _mealState = mutableStateOf(value = MealState(loading = true))
    val mealState: State<MealState> = _mealState

    init {
        fetchMeal()
    }

    private fun fetchMeal() {
        viewModelScope.launch {
            try {
                val response = mealService.getMeal()
                _mealState.value = _mealState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _mealState.value = _mealState.value.copy(
                    loading = false,
                    error = "Error: ${e.message}"
                )
            }
        }
    }

    data class MealState(
        val loading: Boolean,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}