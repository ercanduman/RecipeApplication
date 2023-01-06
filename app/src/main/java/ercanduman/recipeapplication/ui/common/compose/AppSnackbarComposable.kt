package ercanduman.recipeapplication.ui.common.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ercanduman.recipeapplication.ui.common.theme.AppColorWhite
import ercanduman.recipeapplication.ui.common.theme.AppDimenDefaultDistance
import ercanduman.recipeapplication.ui.common.theme.AppText

private const val DEFAULT_ACTION_LABEL = "Hide"

@Composable
fun AppSnackbarComposable(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier,
        snackbar = { snackbarData ->
            Snackbar(
                modifier = Modifier.padding(AppDimenDefaultDistance),
                shape = MaterialTheme.shapes.medium,
                dismissAction = { onDismiss() },
                action = {
                    val actionLabel = snackbarData.visuals.actionLabel ?: DEFAULT_ACTION_LABEL
                    TextButton(onClick = { snackbarData.performAction() }) {
                        AppText(
                            text = actionLabel,
                            textColor = AppColorWhite
                        )
                    }
                }
            ) {
                AppText(
                    text = snackbarData.visuals.message,
                    textColor = AppColorWhite
                )
            }
        }
    )
}
