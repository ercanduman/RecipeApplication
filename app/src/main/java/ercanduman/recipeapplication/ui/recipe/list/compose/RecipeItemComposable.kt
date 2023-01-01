package ercanduman.recipeapplication.ui.recipe.list.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ercanduman.recipeapplication.R
import ercanduman.recipeapplication.common.ui.theme.AppDimenDefaultDistance
import ercanduman.recipeapplication.common.ui.theme.AppText
import ercanduman.recipeapplication.common.ui.theme.AppTextSize20
import ercanduman.recipeapplication.domain.model.Recipe
import ercanduman.recipeapplication.ui.recipe.list.DEFAULT_CONTENT_DESCRIPTION

private const val RECIPE_IMAGE_HEIGHT = 200
private const val INGREDIENTS_TITLE = "Ingredients"

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
        RecipeImageComposable(recipe.imageUrl)
        RecipeContentComposable(recipe)
    }
}

@Composable
private fun RecipeContentComposable(recipe: Recipe) {
    Column(Modifier.padding(AppDimenDefaultDistance)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppText(
                text = recipe.title,
                fontWeight = FontWeight.Bold,
                fontSize = AppTextSize20
            )

            AppText(text = recipe.rating)
        }

        Spacer(modifier = Modifier.padding(top = AppDimenDefaultDistance))
        AppText(
            text = INGREDIENTS_TITLE,
            fontWeight = FontWeight.Bold
        )
        for (ingredient in recipe.ingredients) {
            AppText(
                text = ingredient
            )
        }
    }
}

@Composable
private fun RecipeImageComposable(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = DEFAULT_CONTENT_DESCRIPTION,
        modifier = Modifier
            .fillMaxWidth()
            .height(RECIPE_IMAGE_HEIGHT.dp),
        placeholder = painterResource(id = R.drawable.recipe_placeholder_image),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
private fun RecipeItemComposablePreview() {
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
    RecipeItemComposable(recipe = previewRecipe, onRecipeClick = {})
}
