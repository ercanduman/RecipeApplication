package ercanduman.recipeapplication.ui.common.compose.shimmer

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ercanduman.recipeapplication.ui.common.compose.RECIPE_IMAGE_HEIGHT
import ercanduman.recipeapplication.ui.common.theme.AppDimenDefaultDistance
import ercanduman.recipeapplication.ui.common.theme.AppDimenExtraLargeDistance

private const val ANIMATION_DELAY = 300
private const val ANIMATION_DURATION = 1300
private const val ANIMATION_INITIAL_VALUE = 0f
private const val ANIMATION_TARGET_VALUE = 2000f
private const val ANIMATION_COLOR_ALPHA_VALUE_DARK = 0.9f
private const val ANIMATION_COLOR_ALPHA_VALUE_LIGHT = 0.3f

private const val SHIMMER_RECIPE_ITEM_COUNT = 3
private const val SHIMMER_RECIPE_ITEM_TITLE_HEIGHT = RECIPE_IMAGE_HEIGHT / 10

@Composable
fun RecipeItemShimmerComposable() {
    val transition = rememberInfiniteTransition()

    val animationFloating = transition.animateFloat(
        initialValue = ANIMATION_INITIAL_VALUE,
        targetValue = ANIMATION_TARGET_VALUE,
        animationSpec = infiniteRepeatable(
            animation = tween(
                easing = LinearEasing,
                delayMillis = ANIMATION_DELAY,
                durationMillis = ANIMATION_DURATION
            )
        )
    )

    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = ANIMATION_COLOR_ALPHA_VALUE_DARK),
        Color.LightGray.copy(alpha = ANIMATION_COLOR_ALPHA_VALUE_LIGHT),
        Color.LightGray.copy(alpha = ANIMATION_COLOR_ALPHA_VALUE_DARK)
    )
    val linearGradientBrush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(animationFloating.value, animationFloating.value)
    )

    ShimmerItems(linearGradientBrush)
}

@Composable
private fun ShimmerItems(linearGradientBrush: Brush) {
    Surface(shape = MaterialTheme.shapes.medium) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            repeat(SHIMMER_RECIPE_ITEM_COUNT) {
                ShimmerItem(linearGradientBrush)
            }
        }
    }
}

@Composable
private fun ShimmerItem(linearGradientBrush: Brush) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .height(RECIPE_IMAGE_HEIGHT.dp)
            .background(brush = linearGradientBrush)
    )

    Spacer(modifier = Modifier.padding(top = AppDimenDefaultDistance))
    Spacer(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .fillMaxWidth()
            .height(SHIMMER_RECIPE_ITEM_TITLE_HEIGHT.dp)
            .background(brush = linearGradientBrush)
    )
    Spacer(modifier = Modifier.padding(bottom = AppDimenExtraLargeDistance))
}
