package ercanduman.recipeapplication.data.api.model

import com.google.gson.annotations.SerializedName

data class SearchRecipesResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    @SerializedName(RESULTS_FIELD)
    val recipes: List<RecipeDto>
)

private const val RESULTS_FIELD = "results"
