package ercanduman.recipeapplication.ui.recipe.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ercanduman.recipeapplication.domain.usecase.SearchRecipeUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val INITIAL_PAGE_ID = 1
private const val INITIAL_SEARCH_QUERY = "beef carrot"

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val searchRecipeUseCase: SearchRecipeUseCase
) : ViewModel() {

    var recipeListUiState: MutableState<RecipeListUiState> = mutableStateOf(RecipeListUiState.Loading)
        private set

    var searchQuery: MutableState<String> = mutableStateOf("")
        private set

    init {
        fetchRecipes(INITIAL_PAGE_ID, INITIAL_SEARCH_QUERY)
    }

    fun searchRecipes() {
        val currentPageId: Int = getPageId()
        fetchRecipes(currentPageId, searchQuery.value)
    }

    // FIXME: Get current pageId and Update it based on scroll position.
    private fun getPageId(): Int {
        return INITIAL_PAGE_ID
    }

    private fun fetchRecipes(page: Int, searchQuery: String) {
        viewModelScope.launch {
            recipeListUiState.value = searchRecipeUseCase(
                page = page,
                searchQuery = searchQuery
            )
        }
    }

    fun onQueryChanged(newQuery: String) {
        searchQuery.value = newQuery
    }
}
