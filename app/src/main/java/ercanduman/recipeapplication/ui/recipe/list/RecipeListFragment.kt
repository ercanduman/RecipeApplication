package ercanduman.recipeapplication.ui.recipe.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ercanduman.recipeapplication.R
import ercanduman.recipeapplication.common.ui.theme.AppColorBackgroundGrey
import ercanduman.recipeapplication.common.ui.theme.AppDimenDefaultDistance
import ercanduman.recipeapplication.common.ui.theme.AppText
import ercanduman.recipeapplication.domain.model.Recipe

private const val NAVIGATE_BUTTON_HEIGHT = 56
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
        Surface(modifier = Modifier.background(AppColorBackgroundGrey)) {
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

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    @Composable
    private fun SearchToolbarComposable() {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppDimenDefaultDistance),
            shape = MaterialTheme.shapes.small,
            color = MaterialTheme.colorScheme.primary
        ) {
            Row {
                val query = viewModel.searchQuery.value
                val keyboardController = LocalSoftwareKeyboardController.current

                TextField(
                    value = query,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { viewModel.onQueryChanged(it) },

                    label = {
                        AppText(text = getString(R.string.search))
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
                            viewModel::searchRecipes

                            // Hide keyboard
                            keyboardController?.hide()
                        }
                    )
                )
            }
        }
    }

    @Composable
    private fun CategoryChipsComposable() {
    }

    @Composable
    private fun RecipeListComposable(recipes: List<Recipe>) {
    }
}
