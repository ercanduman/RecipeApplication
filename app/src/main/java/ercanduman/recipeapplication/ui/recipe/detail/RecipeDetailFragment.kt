package ercanduman.recipeapplication.ui.recipe.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ercanduman.recipeapplication.ui.common.theme.AppDimenDefaultDistance
import ercanduman.recipeapplication.ui.common.theme.AppText
import ercanduman.recipeapplication.ui.common.theme.AppTheme
import ercanduman.recipeapplication.ui.recipe.detail.compose.RecipeDetailComposable
import ercanduman.recipeapplication.ui.recipe.detail.compose.RecipeDetailsShimmerComposable
import ercanduman.recipeapplication.ui.recipe.detail.model.RecipeDetailUiState

const val KEY_RECIPE_ID: String = "RecipeDetailFragment.recipeId"
const val INVALID_RECIPE_ID: Int = -1

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {

    private val viewModel: RecipeDetailViewModel by viewModels()

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

    @Composable
    private fun FragmentContent() {
        AppTheme {
            Surface(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(AppDimenDefaultDistance)
            ) {
                when (val uiState = viewModel.recipeDetailUiState.value) {
                    RecipeDetailUiState.Loading -> RecipeDetailsShimmerComposable()

                    is RecipeDetailUiState.Error -> {
                        AppText(
                            text = uiState.errorMessage,
                            textColor = MaterialTheme.colorScheme.error
                        )
                    }
                    is RecipeDetailUiState.Success -> {
                        RecipeDetailComposable(uiState.recipe)
                    }
                }
            }
        }
    }
}
