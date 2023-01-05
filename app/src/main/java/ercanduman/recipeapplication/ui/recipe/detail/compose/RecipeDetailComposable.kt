package ercanduman.recipeapplication.ui.recipe.detail.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import ercanduman.recipeapplication.domain.model.Recipe
import ercanduman.recipeapplication.ui.common.compose.RecipeImageComposable
import ercanduman.recipeapplication.ui.common.theme.AppDimenDefaultDistance
import ercanduman.recipeapplication.ui.common.theme.AppText
import ercanduman.recipeapplication.ui.recipe.list.compose.RecipeTitleAndRatingComposable

private const val INGREDIENTS_TITLE = "Ingredients"

@Composable
fun RecipeDetailComposable(recipe: Recipe) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(AppDimenDefaultDistance)
    ) {
        Column {
            RecipeImageComposable(recipe.imageUrl)

            Column(Modifier.padding(AppDimenDefaultDistance)) {
                RecipeTitleAndRatingComposable(recipe.title, recipe.rating)

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
        fontWeight = FontWeight.Bold
    )
    for (ingredient in ingredients) {
        AppText(text = ingredient)
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeItemDetailComposablePreview() {
    val ingredients = listOf(
        "1/4 teaspoon Dijon mustard",
        "2 hard boiled eggs, chopped",
        "1 tablespoon fresh lemon juice",
        "2 hard boiled egg whites, chopped",
        "2 tablespoons chopped green onion",
        "2 small avocados, pitted and peeled",
        "Salt and freshly ground black pepper, to taste",
        "1 tablespoon plain Greek yogurt (we use Chobani)"
    )
    val previewRecipe = Recipe(
        id = 9,
        title = "Avocado Egg Salad",
        rating = "69",
        imageUrl = "https://nyc3.digitaloceanspaces.com/food2fork/food2fork-static/featured_images/9/featured_image.png",
        ingredients = ingredients
    )
    RecipeDetailComposable(recipe = previewRecipe)
}
