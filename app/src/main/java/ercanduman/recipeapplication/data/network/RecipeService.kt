package ercanduman.recipeapplication.data.network

import ercanduman.recipeapplication.data.network.model.SearchRecipesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipeService {

    @Headers("Authorization: Token 9c8b06d329136da358c2d00e76946b0111ce2c48")
    @GET("search/") // "/search/?page=2&query=beef"
    suspend fun searchRecipes(
        @Query("page") page: Int,
        @Query("query") searchQuery: String
    ): Flow<SearchRecipesResponse>

    @Headers("Authorization: Token 9c8b06d329136da358c2d00e76946b0111ce2c48")
    @GET("get/") // recipe/get/?id=9
    suspend fun getRecipe(
        @Query("id") recipeId: Int
    ): Flow<SearchRecipesResponse>
}
