package ercanduman.recipeapplication.data.repository

import ercanduman.recipeapplication.common.util.RecipeResult
import ercanduman.recipeapplication.data.api.model.RecipeDto
import ercanduman.recipeapplication.data.api.model.SearchRecipesResponse

interface RecipeRepository {

    suspend fun searchRecipes(
        page: Int,
        searchQuery: String
    ): RecipeResult<SearchRecipesResponse>

    suspend fun fetchRecipeDetails(
        recipeId: Int
    ): RecipeResult<RecipeDto>
}
