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
import ercanduman.recipeapplication.common.ui.theme.AppColorDarkGrey
import ercanduman.recipeapplication.common.ui.theme.AppDimenDefaultDistance
import ercanduman.recipeapplication.common.ui.theme.AppDimenSmallDistance
import ercanduman.recipeapplication.domain.model.Recipe
import ercanduman.recipeapplication.ui.recipe.list.compose.RecipeItemListComposable
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

    @Composable
    private fun FragmentContent() {
        Surface(
            modifier = Modifier.background(AppColorDarkGrey)
        ) {
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
        2 - Horizontal scrollable food category Chips
        3 - List of Recipes
    */
    @Composable
    private fun RecipeListMainContentComposable(
        recipes: List<Recipe>
    ) {
        Column(
            modifier = Modifier.padding(
                end = AppDimenDefaultDistance,
                start = AppDimenDefaultDistance
            )
        ) {
            ToolbarContentComposable()
            RecipeListComposable(recipes)
        }
    }

    @Composable
    private fun ToolbarContentComposable() {
        Surface(
            shape = MaterialTheme.shapes.medium,
            shadowElevation = AppDimenSmallDistance
        ) {
            Column(Modifier.padding(AppDimenSmallDistance)) {
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
                    onCategoryClicked = viewModel::onCategoryClicked,
                    selectedCategoryPosition = selectedCategoryPosition,
                    onCategoryPositionChanged = viewModel::onCategoryPositionChanged
                )

                Spacer(modifier = Modifier.padding(bottom = AppDimenSmallDistance))
            }
        }
    }

    @Composable
    private fun RecipeListComposable(recipes: List<Recipe>) {
        LazyColumn(
            contentPadding = PaddingValues(top = AppDimenDefaultDistance),
            verticalArrangement = Arrangement.spacedBy(AppDimenDefaultDistance)
        ) {
            items(items = recipes) { recipe: Recipe ->
                RecipeItemListComposable(
                    recipe = recipe,
                    onRecipeClick = viewModel::onRecipeClicked
                )
            }
        }
    }
}
