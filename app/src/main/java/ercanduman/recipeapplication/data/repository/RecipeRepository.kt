package ercanduman.recipeapplication.data.repository

import ercanduman.recipeapplication.data.api.model.RecipeDto
import ercanduman.recipeapplication.data.api.model.SearchRecipesResponse
import ercanduman.recipeapplication.util.RecipeResult

interface RecipeRepository {

    suspend fun searchRecipes(
        page: Int,
        searchQuery: String
    ): RecipeResult<SearchRecipesResponse>

    suspend fun fetchRecipeDetails(
        recipeId: Int
    ): RecipeResult<RecipeDto>
}
