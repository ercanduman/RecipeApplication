package ercanduman.recipeapplication.data.network.model


import com.google.gson.annotations.SerializedName

data class GetRecipeResponse(
    @SerializedName("pk")
    val pk: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("publisher")
    val publisher: String?,
    @SerializedName("featured_image")
    val featuredImage: String?,
    @SerializedName("rating")
    val rating: Int?,
    @SerializedName("source_url")
    val sourceUrl: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("cooking_instructions")
    val cookingInstructions: List<String>?,
    @SerializedName("ingredients")
    val ingredients: List<String?>?,
    @SerializedName("date_added")
    val dateAdded: String?,
    @SerializedName("date_updated")
    val dateUpdated: String?,
    @SerializedName("long_date_added")
    val longDateAdded: Int?,
    @SerializedName("long_date_updated")
    val longDateUpdated: Int?
)