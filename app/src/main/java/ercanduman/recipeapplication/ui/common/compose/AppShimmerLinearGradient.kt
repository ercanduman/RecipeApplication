package ercanduman.recipeapplication.ui.common.compose

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

private const val ANIMATION_DELAY = 300
private const val ANIMATION_DURATION = 1300
private const val ANIMATION_INITIAL_VALUE = 0f
private const val ANIMATION_TARGET_VALUE = 2000f
private const val ANIMATION_COLOR_ALPHA_VALUE_DARK = 0.9f
private const val ANIMATION_COLOR_ALPHA_VALUE_LIGHT = 0.3f

@Composable
fun appShimmerLinearGradientBrush(): Brush {
    val transition = rememberInfiniteTransition()
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = ANIMATION_COLOR_ALPHA_VALUE_DARK),
        Color.LightGray.copy(alpha = ANIMATION_COLOR_ALPHA_VALUE_LIGHT),
        Color.LightGray.copy(alpha = ANIMATION_COLOR_ALPHA_VALUE_DARK)
    )

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

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(animationFloating.value, animationFloating.value)
    )
}
