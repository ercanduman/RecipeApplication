package ercanduman.recipeapplication.ui.recipe.list.model

enum class FoodCategory(
    val value: String
) {
    CHICKEN("Chicken"),
    BEEF("Beef"),
    SOUP("Soup"),
    DESSERT("Dessert"),
    VEGETARIAN("Vegetarian"),
    MILK("Milk"),
    VEGAN("Vegan"),
    PIZZA("Pizza"),
    DONUT("Donut")
}

sealed class Category {
    object NotProvided : Category()
    data class Provided(
        val foodCategory: FoodCategory
    ) : Category()
}
