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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ercanduman.recipeapplication.BuildConfig
import ercanduman.recipeapplication.R
import ercanduman.recipeapplication.domain.model.Recipe
import ercanduman.recipeapplication.ui.common.compose.AppSnackbarComposable
import ercanduman.recipeapplication.ui.common.compose.shimmer.RecipeShimmerComposable
import ercanduman.recipeapplication.ui.common.theme.AppDimenDefaultDistance
import ercanduman.recipeapplication.ui.common.theme.AppDimenSmallDistance
import ercanduman.recipeapplication.ui.common.theme.AppTheme
import ercanduman.recipeapplication.ui.recipe.detail.INVALID_RECIPE_ID
import ercanduman.recipeapplication.ui.recipe.detail.KEY_RECIPE_ID
import ercanduman.recipeapplication.ui.recipe.list.compose.RecipeListItemComposable
import ercanduman.recipeapplication.ui.recipe.list.compose.toolbar.ChipsToolbarComposable
import ercanduman.recipeapplication.ui.recipe.list.compose.toolbar.SearchToolbarComposable
import ercanduman.recipeapplication.ui.recipe.list.model.RecipeListUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

const val DEFAULT_CONTENT_DESCRIPTION: String = "Recipe app image"

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
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun FragmentContent() {
        AppTheme {
            val coroutineScope = rememberCoroutineScope()
            val snackbarHostState = remember { SnackbarHostState() }

            Scaffold(
                topBar = { ToolbarContentComposable() },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(
                        end = AppDimenDefaultDistance,
                        start = AppDimenDefaultDistance
                    ),
                snackbarHost = {
                    AppSnackbarComposable(
                        snackbarHostState = snackbarHostState,
                        modifier = Modifier.padding(bottom = AppDimenDefaultDistance)
                    )
                }
            ) { innerPaddings ->
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.padding(innerPaddings)
                ) {
                    when (val recipeListUiState = viewModel.recipeListUiState.value) {
                        RecipeListUiState.Loading -> {
                            RecipeShimmerComposable()
                        }

                        is RecipeListUiState.Error -> {
                            showErrorMessageInSnackbar(
                                errorMessage = recipeListUiState.errorMessage,
                                coroutineScope = coroutineScope,
                                snackbarHostState = snackbarHostState
                            )
                        }

                        is RecipeListUiState.Success -> {
                            RecipeContentComposable(recipeListUiState.recipes)
                            checkUiStateForNavigatingToRecipeDetails(recipeListUiState)
                        }
                    }
                }
            }
        }
    }

    private fun showErrorMessageInSnackbar(
        errorMessage: String,
        coroutineScope: CoroutineScope,
        snackbarHostState: SnackbarHostState
    ) {
        if (BuildConfig.DEBUG) Log.d("TAG", errorMessage)
        coroutineScope.launch {
            // If there is already a message on the screen, dismiss it before displaying a new one.
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(
                message = errorMessage,
                duration = SnackbarDuration.Short
            )
        }
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
                    onQueryChange = viewModel::onQueryChanged,
                    onExecuteNewSearch = viewModel::executeNewSearch
                )
                Spacer(modifier = Modifier.padding(bottom = AppDimenDefaultDistance))

                val categories = viewModel.getAllPredefinedFoodCategories()
                val selectedCategory = viewModel.selectedCategory.value
                val selectedCategoryPosition = viewModel.selectedCategoryPosition
                ChipsToolbarComposable(
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onValueChange = viewModel::onQueryChanged,
                    selectedCategoryPosition = selectedCategoryPosition,
                    onCategoryPositionChange = viewModel::onCategoryPositionChanged
                )

                Spacer(modifier = Modifier.padding(bottom = AppDimenSmallDistance))
            }
        }
    }

    @Composable
    private fun RecipeContentComposable(recipes: List<Recipe>) {
        LazyColumn(
            contentPadding = PaddingValues(
                top = AppDimenDefaultDistance,
                bottom = AppDimenDefaultDistance
            ),
            verticalArrangement = Arrangement.spacedBy(AppDimenDefaultDistance)
        ) {
            itemsIndexed(items = recipes) { index: Int, recipe: Recipe ->
                RecipeListItemComposable(
                    recipe = recipe,
                    onRecipeClick = viewModel::onRecipeClicked
                )
                viewModel.onRecipeListScrollPositionChanged(index)
            }
        }
    }

    private fun checkUiStateForNavigatingToRecipeDetails(recipeListUiState: RecipeListUiState.Success) {
        val recipeId = recipeListUiState.recipeId
        if (recipeId != INVALID_RECIPE_ID) {
            val bundle = bundleOf(KEY_RECIPE_ID to recipeId)
            findNavController().navigate(
                args = bundle,
                resId = R.id.action_navigate_to_recipeDetailFragment
            )

            // After navigation, reset the UiState for the clicked item
            viewModel.navigatedToDetails(recipeListUiState)
        }
    }
}
