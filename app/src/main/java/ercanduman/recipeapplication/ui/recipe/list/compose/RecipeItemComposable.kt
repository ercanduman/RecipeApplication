package ercanduman.recipeapplication.ui.recipe.list.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ercanduman.recipeapplication.domain.model.Recipe

@Composable
fun RecipeItemComposable(
    recipe: Recipe,
    onRecipeClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRecipeClick(recipe.id) }
    ) {
    }
}
