package ercanduman.recipeapplication.ui.recipe.list.compose.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

private const val RECIPE_ITEMS_COUNT = 3
private const val RECIPE_ITEM_TITLE_HEIGHT = RECIPE_IMAGE_HEIGHT / 10

@Composable
fun RecipeListShimmerComposable() {
    val linearGradientBrush = appShimmerLinearGradientBrush()
    Surface(shape = MaterialTheme.shapes.medium) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            repeat(RECIPE_ITEMS_COUNT) {
                RecipeShimmerItemComposable(linearGradientBrush)
            }
        }
    }
}

@Composable
private fun RecipeShimmerItemComposable(linearGradientBrush: Brush) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .height(RECIPE_IMAGE_HEIGHT.dp)
            .background(brush = linearGradientBrush)
    )
    Spacer(modifier = Modifier.padding(bottom = AppDimenDefaultDistance))

    Spacer(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .height(RECIPE_ITEM_TITLE_HEIGHT.dp)
            .background(brush = linearGradientBrush)
    )
    Spacer(modifier = Modifier.padding(bottom = AppDimenExtraLargeDistance))
}
