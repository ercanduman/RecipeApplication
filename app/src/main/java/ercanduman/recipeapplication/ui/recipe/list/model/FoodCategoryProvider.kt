package ercanduman.recipeapplication.ui.recipe.list.model

import javax.inject.Inject

class FoodCategoryProvider @Inject constructor() {
    fun allPredefinedFoodCategories(): List<FoodCategory> {
        return FoodCategory.values().toList()
    }

    fun getFoodCategory(categoryName: String): Category {
        val category: Category = try {
            val foodCategory = doesFoodCategoryMatch(categoryName)
            Category.Provided(foodCategory)
        } catch (e: NoSuchElementException) {
            Category.NotProvided
        }
        return category
    }

    private fun doesFoodCategoryMatch(categoryName: String): FoodCategory {
        return enumValues<FoodCategory>().first { it.value == categoryName }
    }
}
