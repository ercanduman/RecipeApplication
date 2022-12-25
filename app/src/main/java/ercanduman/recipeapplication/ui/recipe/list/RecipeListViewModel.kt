package ercanduman.recipeapplication.ui.recipe.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ercanduman.recipeapplication.domain.usecase.SearchRecipeUseCase
import kotlinx.coroutines.launch

class RecipeListViewModel(
    private val searchRecipeUseCase: SearchRecipeUseCase
) : ViewModel() {

    private val initialPageId = 1
    private val initialSearchQuery = "beef"

    init {
        fetchRecipes(initialPageId, initialSearchQuery)
    }

    fun searchRecipes(searchQuery: String) {
        val currentPageId: Int = getPageId()
        fetchRecipes(currentPageId, searchQuery)
    }

    // FIXME: Get current pageId and Update it based on scroll position.
    private fun getPageId(): Int {
        TODO("Not yet implemented")
    }

    private fun fetchRecipes(page: Int, searchQuery: String) {
        viewModelScope.launch {
            searchRecipeUseCase(
                page = page,
                searchQuery = searchQuery
            )
        }
    }
}
