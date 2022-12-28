package ercanduman.recipeapplication.ui.recipe.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ercanduman.recipeapplication.common.ui.theme.AppTheme
import ercanduman.recipeapplication.domain.model.Recipe

private const val NAVIGATE_BUTTON_WIDTH = 180
private const val NAVIGATE_BUTTON_HEIGHT = 56
private const val NAVIGATE_BUTTON_TEXT = "Navigate to Details"

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    // Hilt - An activity or a fragment that is annotated with @AndroidEntryPoint can get the
    // ViewModel instance as normal using the by viewModels()
    // https://developer.android.com/training/dependency-injection/hilt-jetpack#kotlin
    private val viewModel: RecipeListViewModel by viewModels()

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
            when (val recipeListUiState = viewModel.recipeListUiState.value) {
                is RecipeListUiState.Error -> {
                    Log.d("TAG", "FragmentContent: Error")
                }
                RecipeListUiState.Loading -> {
                    Log.d("TAG", "FragmentContent: Loading")
                }
                is RecipeListUiState.Success -> {
                    Log.d("TAG", "FragmentContent: ${recipeListUiState.recipeList.size}")
                    RecipeListMainContentComposable(recipeListUiState.recipeList)
                }
            }
        }
    }

    /*
    This screen will contain 3 major component
        1 - Search Toolbar
        2 - Horizontal scrollable category Chips
        3 - List of Recipes
    */
    @Composable
    private fun RecipeListMainContentComposable(
        recipes: List<Recipe>
    ) {
        Column {
            SearchToolbarComposable()
            CategoryChipsComposable()
            RecipeListComposable(recipes)
        }
    }

    @Composable
    private fun SearchToolbarComposable() {
    }

    @Composable
    private fun CategoryChipsComposable() {
    }

    @Composable
    private fun RecipeListComposable(recipes: List<Recipe>) {
    }
}
