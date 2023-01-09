package ercanduman.recipeapplication.ui.recipe.list.compose.toolbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import ercanduman.recipeapplication.R
import ercanduman.recipeapplication.ui.common.theme.AppText
import ercanduman.recipeapplication.ui.recipe.list.DEFAULT_CONTENT_DESCRIPTION

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchToolbarComposable(
    query: String,
    onQueryChange: (String) -> Unit,
    onExecuteNewSearch: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primary
    ) {
        Row {
            val context = LocalContext.current
            val keyboardController = LocalSoftwareKeyboardController.current

            TextField(
                value = query,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { onQueryChange(it) },

                label = {
                    AppText(text = context.getString(R.string.search))
                },

                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = DEFAULT_CONTENT_DESCRIPTION
                    )
                },

                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    background = MaterialTheme.colorScheme.background
                ),

                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),

                keyboardActions = KeyboardActions(
                    onSearch = {
                        // Start a new search
                        onExecuteNewSearch()

                        // Hide keyboard
                        keyboardController?.hide()
                    }
                )
            )
        }
    }
}
