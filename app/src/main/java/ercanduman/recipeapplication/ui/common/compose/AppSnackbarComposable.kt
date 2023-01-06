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

/**
 * For more details and customization of Snackbar, the following url can be visited.
 * https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary.html#Snackbar(androidx.compose.ui.Modifier,kotlin.Function0,kotlin.Function0,kotlin.Boolean,androidx.compose.ui.graphics.Shape,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,kotlin.Function0)
 */
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
                    snackbarData.visuals.actionLabel?.let { actionLabel ->
                        TextButton(onClick = { snackbarData.performAction() }) {
                            AppText(
                                text = actionLabel,
                                textColor = AppColorWhite
                            )
                        }
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
