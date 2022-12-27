package ercanduman.recipeapplication.domain.mapper

import ercanduman.recipeapplication.data.api.model.RecipeDto
import ercanduman.recipeapplication.domain.model.Recipe
import javax.inject.Inject

class RecipeListMapper @Inject constructor() {
    fun map(recipeDtoList: List<RecipeDto>): List<Recipe> {
        // All fields are mandatory, if any field is NULL, neglect that recipe.
        return recipeDtoList.mapNotNull {
            Recipe(
                title = it.title ?: return@mapNotNull null,
                rating = it.rating ?: return@mapNotNull null,
                imageUrl = it.imageUrl ?: return@mapNotNull null,
                description = it.description ?: return@mapNotNull null,
                ingredients = it.ingredients ?: return@mapNotNull null
            )
        }
    }
}
