package ercanduman.recipeapplication.ui.recipe.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ercanduman.recipeapplication.ui.common.theme.AppDimenDefaultDistance
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
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
                .padding(AppDimenDefaultDistance)
        ) {
            when (val uiState = viewModel.recipeDetailUiState.value) {
                RecipeDetailUiState.Loading -> {
                    // FIXME: Display Shimmer effect
                    Text(text = "Loading...")
                }
                is RecipeDetailUiState.Error -> {
                    // FIXME: Display Error message
                    Text(text = "Error... ${uiState.errorMessage}")
                }
                is RecipeDetailUiState.Success -> {
                    // FIXME: Display Recipe details
                    Text(text = "Recipe Details: \n${uiState.recipe}")
                }
            }
        }
    }
}
