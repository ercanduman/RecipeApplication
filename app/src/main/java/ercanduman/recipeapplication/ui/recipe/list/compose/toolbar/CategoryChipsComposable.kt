package ercanduman.recipeapplication.ui.recipe.list.compose.toolbar

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ercanduman.recipeapplication.common.ui.theme.AppDimenSmallDistance
import ercanduman.recipeapplication.common.ui.theme.AppText
import ercanduman.recipeapplication.ui.recipe.list.model.Category
import ercanduman.recipeapplication.ui.recipe.list.model.FoodCategory

@Composable
fun CategoryChipsComposable(
    categories: List<FoodCategory>,
    selectedCategory: Category,
    onValueChanged: (String) -> Unit,
    horizontalScrollState: ScrollState
) {
    Row(
        modifier = Modifier.horizontalScroll(horizontalScrollState),
        horizontalArrangement = Arrangement.spacedBy(AppDimenSmallDistance)
    ) {
        categories.forEach { category ->
            val isSelected: Boolean = isFoodCategorySelected(category, selectedCategory)
            CategoryChipItemComposable(
                category = category,
                isSelected = isSelected,
                onValueChanged = onValueChanged
            )
        }
    }
}

fun isFoodCategorySelected(category: FoodCategory, selectedCategory: Category): Boolean {
    return (selectedCategory is Category.Provided && category == selectedCategory.foodCategory)
}

@Composable
private fun CategoryChipItemComposable(
    category: FoodCategory,
    isSelected: Boolean = false,
    onValueChanged: (String) -> Unit
) {
    val backgroundColor: Color = if (isSelected) Color.LightGray else MaterialTheme.colorScheme.primary

    Surface(
        shape = MaterialTheme.shapes.small,
        color = backgroundColor,
        modifier = Modifier.toggleable(
            value = isSelected,
            onValueChange = { onValueChanged(category.value) }
        )
    ) {
        AppText(
            text = category.value,
            modifier = Modifier.padding(AppDimenSmallDistance),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}
