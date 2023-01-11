package ercanduman.recipeapplication.domain.mapper

import ercanduman.recipeapplication.R
import ercanduman.recipeapplication.data.api.model.RecipeDto
import ercanduman.recipeapplication.domain.model.Recipe
import ercanduman.recipeapplication.ui.recipe.detail.model.RecipeDetailsUiState
import ercanduman.recipeapplication.util.AppResourcesProvider
import javax.inject.Inject

class RecipeDetailsMapper @Inject constructor(
    private val resourcesProvider: AppResourcesProvider
) {

    fun map(recipeDto: RecipeDto): RecipeDetailsUiState {
        return try {
            // All fields are mandatory, if any field is NULL, neglect that recipe.
            val recipe = Recipe(
                id = recipeDto.recipeId!!,
                title = recipeDto.title!!,
                rating = recipeDto.rating?.toString()!!,
                imageUrl = recipeDto.imageUrl!!,
                ingredients = recipeDto.ingredients!!
            )
            RecipeDetailsUiState.Success(recipe)
        } catch (e: NullPointerException) {
            val errorMessage = resourcesProvider.getString(
                resourceId = R.string.error_null_dto_field,
                substitutingValue = "${e.cause}"
            )
            RecipeDetailsUiState.Error(errorMessage)
        }
    }
}
