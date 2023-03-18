package ercanduman.recipeapplication.ui.recipe.list.compose.toolbar

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import ercanduman.recipeapplication.ui.recipe.list.model.Category
import ercanduman.recipeapplication.ui.recipe.list.model.FoodCategory
import kotlinx.coroutines.launch

@Composable
fun ChipsToolbarComposable(
    foodCategories: List<FoodCategory>,
    selectedCategory: Category,
    selectedCategoryPosition: Int,
    onCategorySelected: (String) -> Unit,
    onCategoryPositionChange: (Int) -> Unit
) {
    val horizontalScrollState = rememberScrollState()
    val rememberCoroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = selectedCategoryPosition) {
        rememberCoroutineScope.launch {
            horizontalScrollState.scrollTo(selectedCategoryPosition)
        }
    }

    CategoryChipsComposable(
        foodCategories = foodCategories,
        selectedCategory = selectedCategory,
        horizontalScrollState = horizontalScrollState,
        onCategorySelected = {
            onCategorySelected(it)
            onCategoryPositionChange(horizontalScrollState.value)
        }
    )
}
