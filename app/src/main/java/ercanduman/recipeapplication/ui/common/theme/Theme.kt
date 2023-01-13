package ercanduman.recipeapplication.ui.common.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ercanduman.recipeapplication.ui.common.compose.AppSnackbarComposable

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

@ExperimentalMaterial3Api
@Composable
fun AppTheme(
    snackbarHostState: SnackbarHostState,
    topBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = Typography,
        colorScheme = LightColorScheme
    ) {
        Scaffold(
            topBar = topBar,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(
                    end = AppDimenDefaultDistance,
                    start = AppDimenDefaultDistance
                ),
            snackbarHost = {
                AppSnackbarComposable(
                    snackbarHostState = snackbarHostState,
                    modifier = Modifier.padding(bottom = AppDimenDefaultDistance)
                )
            }
        ) { innerPaddings ->
            Surface(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(innerPaddings)
            ) {
                content()
            }
        }
    }
}
