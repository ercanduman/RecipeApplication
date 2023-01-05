package ercanduman.recipeapplication.data.repository

import ercanduman.recipeapplication.common.util.RecipeResult
import ercanduman.recipeapplication.common.util.safeApiCall
import ercanduman.recipeapplication.data.api.RecipeService
import ercanduman.recipeapplication.data.api.model.RecipeDto
import ercanduman.recipeapplication.data.api.model.SearchRecipesResponse

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

    override suspend fun getRecipe(
        recipeId: Int
    ): RecipeResult<RecipeDto> {
        return safeApiCall {
            service.getRecipe(recipeId)
        }
    }
}
