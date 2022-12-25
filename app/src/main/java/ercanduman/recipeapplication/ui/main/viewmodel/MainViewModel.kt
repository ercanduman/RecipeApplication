package ercanduman.recipeapplication.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ercanduman.recipeapplication.data.repository.RecipeRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val recipeRepository: RecipeRepository
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

    fun getRecipe(recipeId: Int) {
        viewModelScope.launch {
            recipeRepository.getRecipe(recipeId)
        }
    }

    // FIXME: Get current pageId and Update it based on scroll position.
    private fun getPageId(): Int {
        TODO("Not yet implemented")
    }

    private fun fetchRecipes(pageId: Int, searchQuery: String) {
        viewModelScope.launch {
            recipeRepository.searchRecipes(
                page = pageId,
                searchQuery = searchQuery
            )
        }
    }
}
