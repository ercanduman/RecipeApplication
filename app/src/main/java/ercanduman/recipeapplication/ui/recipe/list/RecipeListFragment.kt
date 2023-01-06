package ercanduman.recipeapplication.ui.recipe.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ercanduman.recipeapplication.domain.model.Recipe
import ercanduman.recipeapplication.ui.common.compose.shimmer.RecipeShimmerComposable
import ercanduman.recipeapplication.ui.common.theme.AppColorBackgroundGrey
import ercanduman.recipeapplication.ui.common.theme.AppDimenDefaultDistance
import ercanduman.recipeapplication.ui.common.theme.AppDimenSmallDistance
import ercanduman.recipeapplication.ui.common.theme.AppTheme
import ercanduman.recipeapplication.ui.recipe.list.compose.RecipeItemComposable
import ercanduman.recipeapplication.ui.recipe.list.compose.toolbar.ChipsToolbarComposable
import ercanduman.recipeapplication.ui.recipe.list.compose.toolbar.SearchToolbarComposable

const val DEFAULT_CONTENT_DESCRIPTION = "Recipe app image"

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

    /*
    This screen will contain 3 major component
        1 - Search Toolbar
        2 - Horizontal scrollable food category Chips
        3 - List of Recipes
    */
    @Composable
    private fun FragmentContent() {
        AppTheme {
            Column(
                modifier = Modifier
                    .background(AppColorBackgroundGrey)
                    .padding(
                        end = AppDimenDefaultDistance,
                        start = AppDimenDefaultDistance
                    )

            ) {
                ToolbarContentComposable()
                Spacer(modifier = Modifier.padding(bottom = AppDimenSmallDistance))

                Surface(
                    shape = MaterialTheme.shapes.medium
                ) {
                    when (val recipeListUiState = viewModel.recipeListUiState.value) {
                        is RecipeListUiState.Error -> {
                            RecipeErrorComposable(recipeListUiState.errorMessage)
                        }
                        RecipeListUiState.Loading -> {
                            RecipeShimmerComposable()
                        }
                        is RecipeListUiState.Success -> {
                            RecipeContentComposable(recipeListUiState.recipeList)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun RecipeErrorComposable(errorMessage: String) {
        Log.d("TAG", "FragmentContent: Error message=$errorMessage")
    }

    @Composable
    private fun ToolbarContentComposable() {
        Surface(
            shape = MaterialTheme.shapes.medium,
            shadowElevation = AppDimenSmallDistance
        ) {
            Column(modifier = Modifier.padding(AppDimenSmallDistance)) {
                val query = viewModel.searchQuery.value
                SearchToolbarComposable(
                    query = query,
                    onQueryChanged = viewModel::onQueryChanged,
                    onExecuteNewSearch = viewModel::executeNewSearch
                )
                Spacer(modifier = Modifier.padding(bottom = AppDimenDefaultDistance))

                val categories = viewModel.getAllPredefinedFoodCategories()
                val selectedCategory = viewModel.selectedCategory.value
                val selectedCategoryPosition = viewModel.selectedCategoryPosition
                ChipsToolbarComposable(
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onValueChanged = viewModel::onQueryChanged,
                    selectedCategoryPosition = selectedCategoryPosition,
                    onCategoryPositionChanged = viewModel::onCategoryPositionChanged
                )

                Spacer(modifier = Modifier.padding(bottom = AppDimenSmallDistance))
            }
        }
    }

    @Composable
    private fun RecipeContentComposable(recipes: List<Recipe>) {
        Log.d("TAG", "FragmentContent: item size=${recipes.size}")
        LazyColumn(
            contentPadding = PaddingValues(bottom = AppDimenDefaultDistance),
            verticalArrangement = Arrangement.spacedBy(AppDimenDefaultDistance)
        ) {
            items(items = recipes) { recipe: Recipe ->
                RecipeItemComposable(
                    recipe = recipe,
                    onRecipeClick = viewModel::onRecipeClicked
                )
            }
        }
    }
}
