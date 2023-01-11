@file:Suppress("HardCodedStringLiteral")

package ercanduman.recipeapplication.util

import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): RecipeResult<T> {
    return try {
        val response = apiCall.invoke()
        val content = response.body()
        if (response.isSuccessful && content != null) {
            RecipeResult.Success(content)
        } else {
            val errorMessage = "No data found. Error: ${response.code()} - ${response.message()}"
            RecipeResult.Error(message = errorMessage)
        }
    } catch (exception: HttpException) {
        val errorMessage = "Network failure. Error: ${exception.code()} - ${exception.message()}"
        RecipeResult.Error(message = errorMessage)
    } catch (throwable: Throwable) {
        val errorMessage = "${throwable.message}"
        RecipeResult.Error(errorMessage)
    }
}
