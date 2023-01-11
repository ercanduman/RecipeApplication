package ercanduman.recipeapplication.ui.recipe.list.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ercanduman.recipeapplication.domain.model.Recipe
import ercanduman.recipeapplication.ui.common.compose.RecipeImageComposable
import ercanduman.recipeapplication.ui.common.theme.AppDimenDefaultDistance

@Composable
fun RecipeListItemComposable(
    recipe: Recipe,
    onRecipeClick: (Int) -> Unit
) {
    Card {
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
