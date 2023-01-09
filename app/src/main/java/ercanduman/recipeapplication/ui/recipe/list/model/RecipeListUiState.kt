package ercanduman.recipeapplication.ui.recipe.list.model

import ercanduman.recipeapplication.domain.model.Recipe
import ercanduman.recipeapplication.ui.recipe.detail.INVALID_RECIPE_ID

sealed class RecipeListUiState {
    object Loading : RecipeListUiState()
    data class Error(
        val errorMessage: String
    ) : RecipeListUiState()

    data class Success(
        val recipes: List<Recipe>,
        val recipeId: Int = INVALID_RECIPE_ID
    ) : RecipeListUiState()
}
