package ercanduman.recipeapplication.domain.usecase

import ercanduman.recipeapplication.data.repository.RecipeRepository

class SearchRecipeUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(page: Int, searchQuery: String) {
        repository.searchRecipes(page, searchQuery)
    }
}
