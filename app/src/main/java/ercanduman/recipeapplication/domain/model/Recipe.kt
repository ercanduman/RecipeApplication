package ercanduman.recipeapplication.domain.model

data class Recipe(
    val title: String,
    val rating: Int,
    val imageUrl: String,
    val description: String,
    val ingredients: List<String>
)
