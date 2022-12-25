package ercanduman.recipeapplication.data.repository

import ercanduman.recipeapplication.common.util.RecipeResult
import ercanduman.recipeapplication.common.util.safeApiCall
import ercanduman.recipeapplication.common.util.safeFlowCall
import ercanduman.recipeapplication.data.api.RecipeService
import ercanduman.recipeapplication.data.api.model.RecipeDto
import ercanduman.recipeapplication.data.api.model.SearchRecipesResponse
import kotlinx.coroutines.flow.Flow

class RecipeRepositoryImpl(
    private val service: RecipeService
) : RecipeRepository {
    override suspend fun searchRecipes(
        page: Int,
        searchQuery: String
    ): Flow<RecipeResult<SearchRecipesResponse>> {
        return safeFlowCall {
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
