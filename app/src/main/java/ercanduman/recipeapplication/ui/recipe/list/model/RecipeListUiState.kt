package ercanduman.recipeapplication.ui.recipe.list.model

import ercanduman.recipeapplication.domain.model.Recipe

/**
 * UI events:
 * https://developer.android.com/topic/architecture/ui-layer/events#compose
 *
 * Handle ViewModel events:
 * https://developer.android.com/topic/architecture/ui-layer/events#handle-viewmodel-events
 */
data class RecipeListUiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val recipeId: Int = INVALID_RECIPE_ID,
    val errorMessage: String = INVALID_ERROR_MESSAGE
)

const val KEY_RECIPE_ID: String = "RecipeDetailsFragment.recipeId"
const val INVALID_RECIPE_ID: Int = -1
const val INVALID_ERROR_MESSAGE: String = ""
