package ercanduman.recipeapplication.domain.usecase

import ercanduman.recipeapplication.data.repository.RecipeRepository
import javax.inject.Inject

class SearchRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(page: Int, searchQuery: String) {
        repository.searchRecipes(page, searchQuery)
    }
}
