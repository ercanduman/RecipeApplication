@file:Suppress("HardCodedStringLiteral")

package ercanduman.recipeapplication.data.api.model

import com.google.gson.annotations.SerializedName

data class SearchRecipesResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    @SerializedName("results")
    val recipes: List<RecipeDto>
)
