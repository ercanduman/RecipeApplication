package ercanduman.recipeapplication.domain.usecase

import ercanduman.recipeapplication.data.repository.RecipeRepository

class GetRecipeUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipeId: Int) {
        repository.getRecipe(recipeId)
    }
}
