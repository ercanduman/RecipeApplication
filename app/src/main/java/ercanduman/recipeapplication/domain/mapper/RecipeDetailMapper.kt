package ercanduman.recipeapplication.domain.mapper

import android.util.Log
import ercanduman.recipeapplication.BuildConfig
import ercanduman.recipeapplication.data.api.model.RecipeDto
import ercanduman.recipeapplication.domain.model.Recipe
import ercanduman.recipeapplication.ui.recipe.detail.model.RecipeDetailUiState
import javax.inject.Inject

private const val TAG = "RecipeDetailMapper"

class RecipeDetailMapper @Inject constructor() {

    fun map(recipeDto: RecipeDto): RecipeDetailUiState {
        return try {
            // All fields are mandatory, if any field is NULL, neglect that recipe.
            val recipe = Recipe(
                id = recipeDto.recipeId!!,
                title = recipeDto.title!!,
                rating = recipeDto.rating?.toString()!!,
                imageUrl = recipeDto.imageUrl!!,
                ingredients = recipeDto.ingredients!!
            )
            RecipeDetailUiState.Success(recipe)
        } catch (e: NullPointerException) {
            val errorMessage = "RecipeDto fields should not be null! ${e.cause}"
            if (BuildConfig.DEBUG) Log.e(TAG, errorMessage)
            RecipeDetailUiState.Error(errorMessage)
        }
    }
}
