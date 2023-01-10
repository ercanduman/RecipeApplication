package ercanduman.recipeapplication.data.repository

import ercanduman.recipeapplication.data.api.RecipeService
import ercanduman.recipeapplication.data.api.model.RecipeDto
import ercanduman.recipeapplication.data.api.model.SearchRecipesResponse
import ercanduman.recipeapplication.util.RecipeResult
import ercanduman.recipeapplication.util.safeApiCall

class RecipeRepositoryImpl(
    private val service: RecipeService
) : RecipeRepository {
    override suspend fun searchRecipes(
        page: Int,
        searchQuery: String
    ): RecipeResult<SearchRecipesResponse> {
        return safeApiCall {
            service.searchRecipes(page, searchQuery)
        }
    }

    override suspend fun fetchRecipeDetails(
        recipeId: Int
    ): RecipeResult<RecipeDto> {
        return safeApiCall {
            service.fetchRecipeDetails(recipeId)
        }
    }
}
