@file:Suppress("HardCodedStringLiteral")

package ercanduman.recipeapplication.util

sealed class RecipeResult<out T> {
    object Loading : RecipeResult<Nothing>()
    data class Error(val message: String) : RecipeResult<Nothing>()
    data class Success<T>(val data: T) : RecipeResult<T>()

    override fun toString(): String {
        return when (this) {
            Loading -> "Loading"
            is Success -> "Success[data=$data]"
            is Error -> "Error[message=$message]"
        }
    }
}
