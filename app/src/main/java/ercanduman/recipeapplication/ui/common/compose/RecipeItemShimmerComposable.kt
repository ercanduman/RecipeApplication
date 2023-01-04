package ercanduman.recipeapplication.ui.common.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RecipeItemShimmerComposable(
    shimmerColors: List<Color>
) {
    val linearGradientBrush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(200f, 200f),
        end = Offset(400f, 200f)
    )

    Surface(shape = MaterialTheme.shapes.medium) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(RECIPE_IMAGE_HEIGHT.dp)
                .background(brush = linearGradientBrush)
        )
    }
}
