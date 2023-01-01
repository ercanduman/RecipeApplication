package ercanduman.recipeapplication.ui.recipe.list.compose

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ercanduman.recipeapplication.common.ui.theme.AppDimenSmallDistance
import ercanduman.recipeapplication.common.ui.theme.AppText
import ercanduman.recipeapplication.ui.recipe.list.model.FoodCategory
import ercanduman.recipeapplication.ui.recipe.list.model.FoodCategoryProvider

@Composable
fun CategoryChipsComposable(categories: List<FoodCategory>) {
    val horizontalScrollState = rememberScrollState()

    Row(
        modifier = Modifier.horizontalScroll(horizontalScrollState),
        horizontalArrangement = Arrangement.spacedBy(AppDimenSmallDistance)
    ) {
        categories.forEach { category ->
            CategoryChipItemComposable(category)
        }
    }
}

@Composable
private fun CategoryChipItemComposable(category: FoodCategory) {
    Card(
        shape = MaterialTheme.shapes.small
    ) {
        AppText(
            text = category.value,
            modifier = Modifier
                .padding(AppDimenSmallDistance)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryChipsComposablePreview() {
    val categoryProvider = FoodCategoryProvider()
    CategoryChipsComposable(categories = categoryProvider.allPredefinedFoodCategories())
}
