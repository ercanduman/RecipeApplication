package ercanduman.recipeapplication.ui.recipe.detail.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import ercanduman.recipeapplication.ui.common.compose.RECIPE_IMAGE_HEIGHT
import ercanduman.recipeapplication.ui.common.compose.appShimmerLinearGradientBrush
import ercanduman.recipeapplication.ui.common.theme.AppDimenDefaultDistance
import ercanduman.recipeapplication.ui.common.theme.AppDimenExtraLargeDistance

private const val RECIPE_INGREDIENTS_COUNT = 6
private const val RECIPE_INGREDIENTS_TITLE_HEIGHT = RECIPE_IMAGE_HEIGHT / 5
private const val RECIPE_INGREDIENTS_HEIGHT = RECIPE_IMAGE_HEIGHT / 10

@Composable
fun RecipeDetailsShimmerComposable() {
    val shimmerLinearGradientBrush = appShimmerLinearGradientBrush()
    Surface(shape = MaterialTheme.shapes.medium) {
        RecipeShimmerItemComposable(linearGradientBrush = shimmerLinearGradientBrush)
    }
}

@Composable
private fun RecipeShimmerItemComposable(linearGradientBrush: Brush) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Image shimmer
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .height(RECIPE_IMAGE_HEIGHT.dp)
                .background(brush = linearGradientBrush)
        )
        Spacer(modifier = Modifier.padding(bottom = AppDimenDefaultDistance))

        // Ingredients title shimmer
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .height(RECIPE_INGREDIENTS_TITLE_HEIGHT.dp)
                .background(brush = linearGradientBrush)
        )
        Spacer(modifier = Modifier.padding(bottom = AppDimenDefaultDistance))

        // Ingredients items shimmer
        repeat(RECIPE_INGREDIENTS_COUNT) {
            Spacer(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .fillMaxWidth()
                    .height(RECIPE_INGREDIENTS_HEIGHT.dp)
                    .background(brush = linearGradientBrush)
            )
            Spacer(modifier = Modifier.padding(bottom = AppDimenExtraLargeDistance))
        }
    }
}
