package ercanduman.recipeapplication.ui.recipe.list

import ercanduman.recipeapplication.domain.model.Recipe

sealed class RecipeListUiState {
    object Loading : RecipeListUiState()
    data class Error(
        val errorMessage: String
    ) : RecipeListUiState()

    data class Success(
        val recipeList: List<Recipe>
    ) : RecipeListUiState()
}
