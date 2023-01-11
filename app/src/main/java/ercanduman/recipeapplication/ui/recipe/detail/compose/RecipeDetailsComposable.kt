package ercanduman.recipeapplication.ui.recipe.detail.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import ercanduman.recipeapplication.domain.model.Recipe
import ercanduman.recipeapplication.ui.common.compose.RecipeImageComposable
import ercanduman.recipeapplication.ui.common.theme.AppDimenDefaultDistance
import ercanduman.recipeapplication.ui.common.theme.AppText
import ercanduman.recipeapplication.ui.common.theme.AppTextSize20
import ercanduman.recipeapplication.ui.recipe.list.compose.RecipeTitleAndRatingComposable

private const val INGREDIENTS_TITLE = "Ingredients"
private const val TITLE_MAX_LINES = Int.MAX_VALUE

@Composable
fun RecipeDetailsComposable(recipe: Recipe) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Column {
            RecipeImageComposable(recipe.imageUrl)

            Column(Modifier.padding(AppDimenDefaultDistance)) {
                RecipeTitleAndRatingComposable(
                    title = recipe.title,
                    rating = recipe.rating,
                    titleMaxLines = TITLE_MAX_LINES
                )

                Spacer(modifier = Modifier.padding(top = AppDimenDefaultDistance))
                RecipeIngredientsComposable(recipe.ingredients)
            }
        }
    }
}

@Composable
private fun RecipeIngredientsComposable(ingredients: List<String>) {
    AppText(
        text = INGREDIENTS_TITLE,
        fontSize = AppTextSize20,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.padding(bottom = AppDimenDefaultDistance))
    for (ingredient in ingredients) {
        AppText(text = ingredient)
    }
}
