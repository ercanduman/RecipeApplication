package ercanduman.recipeapplication.ui.common.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ercanduman.recipeapplication.ui.common.theme.AppColorWhite
import ercanduman.recipeapplication.ui.common.theme.AppText

private const val DEFAULT_ACTION_LABEL = ""

@Composable
fun AppSnackbarComposable(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onDismiss: (() -> Unit)? = null
) {
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier,
        snackbar = { snackbarData ->
            Snackbar(
                shape = MaterialTheme.shapes.medium,
                dismissAction = { onDismiss?.invoke() },
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
