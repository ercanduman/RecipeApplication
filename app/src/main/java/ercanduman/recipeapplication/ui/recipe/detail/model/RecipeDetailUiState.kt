package ercanduman.recipeapplication.ui.recipe.detail.model

import ercanduman.recipeapplication.domain.model.Recipe

sealed class RecipeDetailUiState {
    object Loading : RecipeDetailUiState()
    data class Error(
        val errorMessage: String
    ) : RecipeDetailUiState()

    data class Success(
        val recipe: Recipe
    ) : RecipeDetailUiState()
}
