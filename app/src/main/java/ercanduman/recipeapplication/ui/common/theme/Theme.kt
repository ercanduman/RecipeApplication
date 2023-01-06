package ercanduman.recipeapplication.ui.common.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = AppColorPrimary,
    onPrimary = AppColorOnPrimary,
    primaryContainer = AppColorPrimaryContainer,
    onPrimaryContainer = AppColorOnPrimaryContainer,

    background = AppColorBackgroundGrey,

    secondary = AppColorSecondary,
    onSecondary = AppColorOnSecondary,
    secondaryContainer = AppColorSecondaryContainer,
    onSecondaryContainer = AppColorOnSecondaryContainer
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
