package ercanduman.recipeapplication.domain.usecase

import ercanduman.recipeapplication.data.repository.RecipeRepository
import javax.inject.Inject

class GetRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipeId: Int) {
        repository.getRecipe(recipeId)
    }
}
