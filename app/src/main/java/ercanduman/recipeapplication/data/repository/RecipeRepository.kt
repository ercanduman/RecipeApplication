package ercanduman.recipeapplication.data.repository

import ercanduman.recipeapplication.common.util.RecipeResult
import ercanduman.recipeapplication.data.api.model.GetRecipeResponse
import ercanduman.recipeapplication.data.api.model.SearchRecipesResponse
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun searchRecipes(
        page: Int,
        searchQuery: String
    ): Flow<RecipeResult<SearchRecipesResponse>>

    suspend fun getRecipe(
        recipeId: Int
    ): RecipeResult<GetRecipeResponse>
}
