@file:Suppress("HardCodedStringLiteral")
@file:OptIn(ExperimentalCoroutinesApi::class)

package ercanduman.recipeapplication.data.repository

import com.google.common.truth.Truth.assertThat
import ercanduman.recipeapplication.data.api.RecipeService
import ercanduman.recipeapplication.data.api.model.RecipeDto
import ercanduman.recipeapplication.data.api.model.SearchRecipesResponse
import ercanduman.recipeapplication.util.RecipeResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

class RecipeRepositoryTest {

    private val service: RecipeService = mockk()
    private val repository: RecipeRepository = RecipeRepositoryImpl(service)

    @Test
    fun `If RecipeService responds successfully, RecipeRepository should show success result for searchRecipes{}`() {
        runTest {
            val searchRecipesResponse = SearchRecipesResponse(
                count = 1,
                next = "",
                previous = "",
                recipes = listOf(testRecipeDto)
            )
            val successResponse: Response<SearchRecipesResponse> = Response.success(searchRecipesResponse)
            coEvery { service.searchRecipes(any(), any()) } returns successResponse

            val successResult: RecipeResult<SearchRecipesResponse> = repository.searchRecipes(1, "")

            assertThat(successResult).isInstanceOf(RecipeResult.Success::class.java)
        }
    }

    @Test
    fun `If RecipeService responds successfully, RecipeRepository should show success result with correct content for searchRecipes{}`() {
        runTest {
            val searchRecipesResponse = SearchRecipesResponse(
                count = 1,
                next = "",
                previous = "",
                recipes = listOf(testRecipeDto)
            )
            val successResponse: Response<SearchRecipesResponse> = Response.success(searchRecipesResponse)
            coEvery { service.searchRecipes(any(), any()) } returns successResponse

            val successResult: RecipeResult<SearchRecipesResponse> = repository.searchRecipes(1, "")

            assertThat(successResult).isInstanceOf(RecipeResult.Success::class.java)

            val actualRecipeDtoContent = (successResult as RecipeResult.Success).data.recipes.first()
            assertThat(actualRecipeDtoContent).isEqualTo(testRecipeDto)
        }
    }

    @Test
    fun `If RecipeService responds with an Error, RecipeRepository should show Error result with error message for searchRecipes{}`() {
        runTest {
            val errorResponseBody: ResponseBody = "Some JSON content".toResponseBody("application/json".toMediaTypeOrNull())
            val errorResponse: Response<SearchRecipesResponse> = Response.error(404, errorResponseBody)

            coEvery { service.searchRecipes(any(), any()) } returns errorResponse
            val errorResult: RecipeResult<SearchRecipesResponse> = repository.searchRecipes(1, "")
            assertThat(errorResult).isInstanceOf(RecipeResult.Error::class.java)

            val actualErrorMessage = (errorResult as RecipeResult.Error).message
            // This must be same as in SafeApiCall
            val generatedErrorMessage = "No data found. Error: ${errorResponse.code()} - ${errorResponse.message()}"

            assertThat(actualErrorMessage).isEqualTo(generatedErrorMessage)
        }
    }

    @Test
    fun `If RecipeService responds successfully, RecipeRepository should show success result for fetchRecipeDetails{}`() {
        runTest {
            val searchRecipesResponse: RecipeDto = testRecipeDto

            val successResponse: Response<RecipeDto> = Response.success(searchRecipesResponse)
            coEvery { service.fetchRecipeDetails(any()) } returns successResponse

            val successResult: RecipeResult<RecipeDto> = repository.fetchRecipeDetails(recipeId = 1)

            assertThat(successResult).isInstanceOf(RecipeResult.Success::class.java)
        }
    }

    @Test
    fun `If RecipeService responds successfully, RecipeRepository should show success result with correct content for fetchRecipeDetails{}`() {
        runTest {
            val searchRecipesResponse: RecipeDto = testRecipeDto

            val successResponse: Response<RecipeDto> = Response.success(searchRecipesResponse)
            coEvery { service.fetchRecipeDetails(any()) } returns successResponse

            val successResult: RecipeResult<RecipeDto> = repository.fetchRecipeDetails(recipeId = 1)

            assertThat(successResult).isInstanceOf(RecipeResult.Success::class.java)

            val actualRecipeDtoContent = (successResult as RecipeResult.Success).data
            assertThat(actualRecipeDtoContent).isEqualTo(testRecipeDto)
        }
    }

    @Test
    fun `If RecipeService responds with an Error, RecipeRepository should show Error result with error message for fetchRecipeDetails{}`() {
        runTest {
            val errorResponseBody: ResponseBody = "Some JSON content".toResponseBody("application/json".toMediaTypeOrNull())
            val errorResponse: Response<RecipeDto> = Response.error(404, errorResponseBody)

            coEvery { service.fetchRecipeDetails(any()) } returns errorResponse

            val errorResult: RecipeResult<RecipeDto> = repository.fetchRecipeDetails(recipeId = 1)

            assertThat(errorResult).isInstanceOf(RecipeResult.Error::class.java)

            val actualErrorMessage = (errorResult as RecipeResult.Error).message
            // This must be same as in SafeApiCall
            val generatedErrorMessage = "No data found. Error: ${errorResponse.code()} - ${errorResponse.message()}"

            assertThat(actualErrorMessage).isEqualTo(generatedErrorMessage)
        }
    }
}
