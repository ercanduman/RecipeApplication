package ercanduman.recipeapplication.domain.model

data class Recipe(
    val id: Int,
    val title: String,
    val rating: Int,
    val imageUrl: String,
    val description: String,
    val ingredients: List<String>
)
