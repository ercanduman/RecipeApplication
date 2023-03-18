@file:Suppress("HardCodedStringLiteral")

package ercanduman.recipeapplication.data.api

import ercanduman.recipeapplication.data.api.model.RecipeDto
import ercanduman.recipeapplication.data.api.model.SearchRecipesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipeService {
    // Full url: https://food2fork.ca/api/recipe/search/?page=2&query=beef
    @Headers(HTTP_HEADERS)
    @GET("search/") // "search/?page=2&query=beef"
    suspend fun searchRecipes(
        @Query("page") page: Int,
        @Query("query") searchQuery: String
    ): Response<SearchRecipesResponse>

    // Full url: https://food2fork.ca/api/recipe/get/?id=9
    @Headers(HTTP_HEADERS)
    @GET("get/") // get/?id=9
    suspend fun fetchRecipeDetails(
        @Query("id") recipeId: Int
    ): Response<RecipeDto>
}

private const val HTTP_HEADERS = "Authorization: Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
