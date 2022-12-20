package ercanduman.recipeapplication.data.repository

import ercanduman.recipeapplication.common.util.RecipeResult
import ercanduman.recipeapplication.common.util.safeApiCall
import ercanduman.recipeapplication.data.api.RecipeService
import ercanduman.recipeapplication.data.api.model.GetRecipeResponse
import ercanduman.recipeapplication.data.api.model.SearchRecipesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeRepositoryImpl(
    private val service: RecipeService
) : RecipeRepository {
    override suspend fun searchRecipes(
        page: Int,
        searchQuery: String
    ): Flow<RecipeResult<SearchRecipesResponse>> {
        return flow {
            emit(RecipeResult.Loading)
            try {
                val response = service.searchRecipes(page, searchQuery)

                if (response.isSuccessful && response.body() != null) {
                    emit(RecipeResult.Success(response.body()!!))
                } else {
                    emit(
                        RecipeResult.Error(
                            message = "No data found. ${response.code()} - ${response.errorBody()}"
                        )
                    )
                }
            } catch (e: Throwable) {
                emit(RecipeResult.Error(e.message ?: "An error occurred during api call."))
            }
        }
    }

    override suspend fun getRecipe(
        recipeId: Int
    ): RecipeResult<GetRecipeResponse> {
        return safeApiCall {
            service.getRecipe(recipeId)
        }
    }
}
