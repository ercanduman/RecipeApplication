package ercanduman.recipeapplication.domain.usecase

import ercanduman.recipeapplication.common.util.RecipeResult
import ercanduman.recipeapplication.data.repository.RecipeRepository
import ercanduman.recipeapplication.domain.mapper.RecipeListMapper
import ercanduman.recipeapplication.domain.model.Recipe
import ercanduman.recipeapplication.ui.recipe.list.RecipeListUiState
import javax.inject.Inject

class SearchRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository,
    private val recipeListMapper: RecipeListMapper
) {
    private val currentRecipeList: MutableList<Recipe> = mutableListOf()

    suspend operator fun invoke(
        page: Int,
        searchQuery: String
    ): RecipeListUiState {
        return when (val searchResult = repository.searchRecipes(page, searchQuery)) {
            RecipeResult.Loading -> {
                RecipeListUiState.Loading
            }

            is RecipeResult.Error -> {
                val errorMessage: String = searchResult.message
                RecipeListUiState.Error(errorMessage)
            }

            is RecipeResult.Success -> {
                val recipeDtoList = searchResult.data.recipes
                val recipes: List<Recipe> = recipeListMapper.map(recipeDtoList)
                appendRecipesToCurrentRecipeList(recipes)
            }
        }
    }

    private fun appendRecipesToCurrentRecipeList(recipes: List<Recipe>): RecipeListUiState {
        // Append new recipes to the current list of recipes for pagination.
        currentRecipeList.addAll(recipes)

        // Immutable version of list must be provided to Compose components
        val appendedRecipeList: List<Recipe> = currentRecipeList.toList()
        return if (appendedRecipeList.isEmpty()) {
            RecipeListUiState.Error("No data found!")
        } else {
            RecipeListUiState.Success(appendedRecipeList)
        }
    }

    fun clearCurrentRecipeList() {
        currentRecipeList.clear()
    }
}
