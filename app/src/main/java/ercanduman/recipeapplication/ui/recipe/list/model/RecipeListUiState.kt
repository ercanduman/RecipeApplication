package ercanduman.recipeapplication.ui.recipe.list.model

import ercanduman.recipeapplication.domain.model.Recipe
import ercanduman.recipeapplication.ui.recipe.detail.INVALID_ERROR_MESSAGE
import ercanduman.recipeapplication.ui.recipe.detail.INVALID_RECIPE_ID

data class RecipeListUiState(
    val isLoading: Boolean = true,
    val recipes: List<Recipe> = emptyList(),
    val recipeId: Int = INVALID_RECIPE_ID,
    val errorMessage: String = INVALID_ERROR_MESSAGE,
)
