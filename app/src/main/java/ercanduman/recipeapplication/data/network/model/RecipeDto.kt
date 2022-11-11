package ercanduman.recipeapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class RecipeDto(
    @SerializedName("pk")
    val id: Int,
    val title: String,
    val rating: Int,
    val publisher: String,
    val description: String,
    val ingredients: List<String>,
    @SerializedName("featured_image")
    val imageUrl: String,
    @SerializedName("source_url")
    val sourceUrl: String,
    @SerializedName("date_added")
    val dateAdded: String,
    @SerializedName("date_updated")
    val dateUpdated: String,
    @SerializedName("long_date_added")
    val longDateAdded: Int,
    @SerializedName("long_date_updated")
    val longDateUpdated: Int
)
