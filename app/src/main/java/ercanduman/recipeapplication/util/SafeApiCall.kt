package ercanduman.recipeapplication.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
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
        val errorMessage =
            "Unexpected exception occurred during network call. Error: ${throwable.message}"
        RecipeResult.Error(errorMessage)
    }
}

private val initialResult = RecipeResult.Loading

suspend fun <T> safeFlowCall(
    apiCall: suspend () -> Response<T>
): Flow<RecipeResult<T>> {
    return flow {
        emit(initialResult)
        val response = safeApiCall { apiCall.invoke() }
        emit(response)
    }.catch {
        val errorMessage = "Unexpected Exception occurred during Flow call.$it"
        emit(RecipeResult.Error(errorMessage))
    }
}
