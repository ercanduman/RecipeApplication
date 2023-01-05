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
    categories: List<FoodCategory>,
    selectedCategory: Category,
    selectedCategoryPosition: Int,
    onValueChanged: (String) -> Unit,
    onCategoryPositionChanged: (Int) -> Unit
) {
    val horizontalScrollState = rememberScrollState()
    val rememberCoroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = selectedCategoryPosition) {
        rememberCoroutineScope.launch {
            horizontalScrollState.scrollTo(selectedCategoryPosition)
        }
    }

    CategoryChipsComposable(
        categories = categories,
        selectedCategory = selectedCategory,
        horizontalScrollState = horizontalScrollState,
        onValueChanged = {
            onValueChanged(it)
            onCategoryPositionChanged(horizontalScrollState.value)
        }
    )
}
