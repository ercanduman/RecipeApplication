package ercanduman.recipeapplication.ui.recipe.list.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ercanduman.recipeapplication.common.ui.theme.AppDimenDefaultDistance
import ercanduman.recipeapplication.domain.model.Recipe

@Composable
fun RecipeItemListComposable(
    recipe: Recipe,
    onRecipeClick: (Int) -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(AppDimenDefaultDistance)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onRecipeClick(recipe.id) }
        ) {
            RecipeImageComposable(recipe.imageUrl)

            Column(Modifier.padding(AppDimenDefaultDistance)) {
                RecipeTitleAndRatingComposable(recipe.title, recipe.rating)
            }
        }
    }
}
