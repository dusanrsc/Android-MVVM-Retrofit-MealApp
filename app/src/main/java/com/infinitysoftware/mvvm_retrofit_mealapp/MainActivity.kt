package com.infinitysoftware.mvvm_retrofit_mealapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.infinitysoftware.mvvm_retrofit_mealapp.ui.theme.MVVMRetrofitMealAppTheme
import com.infinitysoftware.mvvm_retrofit_mealapp.view.MealView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVVMRetrofitMealAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MealView(modifier = Modifier.padding(paddingValues = innerPadding))
                }
            }
        }
    }
}