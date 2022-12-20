package ercanduman.recipeapplication.data

import ercanduman.recipeapplication.data.api.model.GetRecipeResponse
import ercanduman.recipeapplication.data.api.model.SearchRecipesResponse
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun searchRecipes(
        page: Int,
        searchQuery: String
    ): Flow<SearchRecipesResponse>

    suspend fun getRecipe(
        recipeId: Int
    ): GetRecipeResponse
}
