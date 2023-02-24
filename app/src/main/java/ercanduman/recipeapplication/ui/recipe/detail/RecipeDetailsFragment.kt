package ercanduman.recipeapplication.ui.recipe.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ercanduman.recipeapplication.ui.common.compose.showErrorMessageInSnackbar
import ercanduman.recipeapplication.ui.common.theme.AppText
import ercanduman.recipeapplication.ui.common.theme.AppTheme
import ercanduman.recipeapplication.ui.recipe.detail.compose.RecipeDetailsComposable
import ercanduman.recipeapplication.ui.recipe.detail.compose.RecipeDetailsShimmerComposable
import ercanduman.recipeapplication.ui.recipe.detail.model.RecipeDetailsUiState

const val KEY_RECIPE_ID: String = "RecipeDetailsFragment.recipeId"
const val INVALID_RECIPE_ID: Int = -1
const val INVALID_ERROR_MESSAGE: String = ""

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment() {

    private val viewModel: RecipeDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recipeId = arguments?.getInt(KEY_RECIPE_ID, INVALID_RECIPE_ID) ?: INVALID_RECIPE_ID
        viewModel.fetchRecipeDetails(recipeId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent { FragmentContent() }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun FragmentContent() {
        val coroutineScope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        AppTheme(
            snackbarHostState = snackbarHostState
        ) {
            when (val uiState = viewModel.detailsUiState.value) {
                RecipeDetailsUiState.Loading -> RecipeDetailsShimmerComposable()
                is RecipeDetailsUiState.Success -> RecipeDetailsComposable(uiState.recipe)

                is RecipeDetailsUiState.Error -> {
                    AppText(
                        text = uiState.errorMessage,
                        textColor = MaterialTheme.colorScheme.error
                    )

                    showErrorMessageInSnackbar(
                        errorMessage = uiState.errorMessage,
                        coroutineScope = coroutineScope,
                        snackbarHostState = snackbarHostState
                    )
                }
            }
        }
    }
}
