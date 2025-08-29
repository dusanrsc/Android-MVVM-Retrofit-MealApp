package com.infinitysoftware.mvvm_retrofit_mealapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.infinitysoftware.mvvm_retrofit_mealapp.model.Category
import com.infinitysoftware.mvvm_retrofit_mealapp.ui.theme.DefaultAppBackgroundColor
import com.infinitysoftware.mvvm_retrofit_mealapp.viewmodel.MealViewModel

@Composable
fun MealView(modifier: Modifier = Modifier) {
    val mealViewModel: MealViewModel = viewModel()
    val viewState by mealViewModel.mealState

    Box(
        modifier = modifier.fillMaxSize().background(DefaultAppBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        when {
            viewState.loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(size = 150.dp),
                    color = Color.White,
                    strokeWidth = 4.dp
                )
            }
            viewState.error != null -> {
                SelectionContainer {
                    Text(
                        text = "Error: ${viewState.error}",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(all = 16.dp)
                    )
                }
            }
            else -> {
                MealScreen(meals = viewState.list)
            }
        }
    }
}

@Composable
fun MealScreen(meals: List<Category>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        items(items = meals) { meal ->
            MealItem(meal = meal)
        }
    }
}

@Composable
fun MealItem(meal: Category) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(all = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = meal.strCategoryThumb),
                contentDescription = meal.strCategory,
                modifier = Modifier.size(size = 150.dp)
            )
            Text(
                text = meal.strCategory,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}