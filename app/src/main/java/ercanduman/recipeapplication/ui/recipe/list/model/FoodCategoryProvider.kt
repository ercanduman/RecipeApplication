package ercanduman.recipeapplication.ui.recipe.list.model

import javax.inject.Inject

class FoodCategoryProvider @Inject constructor() {
    fun allPredefinedFoodCategories(): List<FoodCategory> {
        return FoodCategory.values().toList()
    }

    fun getFoodCategory(categoryName: String): Category {
        val category: Category = try {
            val foodCategory = FoodCategory.valueOf(categoryName)
            Category.Provided(foodCategory)
        } catch (e: IllegalArgumentException) {
            Category.NotProvided
        }
        return category
    }
}
