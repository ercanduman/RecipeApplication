package ercanduman.recipeapplication.data.api.model

import com.google.gson.annotations.SerializedName

data class RecipeDto(
    @SerializedName("pk")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("rating")
    val rating: Int?,
    @SerializedName("publisher")
    val publisher: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("ingredients")
    val ingredients: List<String>?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("source_url")
    val sourceUrl: String?,
    @SerializedName("date_added")
    val dateAdded: String?,
    @SerializedName("date_updated")
    val dateUpdated: String?,
    @SerializedName("long_date_added")
    val longDateAdded: Int?,
    @SerializedName("long_date_updated")
    val longDateUpdated: Int
)
