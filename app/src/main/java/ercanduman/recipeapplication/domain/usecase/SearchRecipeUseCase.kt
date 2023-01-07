package ercanduman.recipeapplication.domain.usecase

import ercanduman.recipeapplication.common.util.RecipeResult
import ercanduman.recipeapplication.data.api.model.RecipeDto
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
                val recipeDtoList: List<RecipeDto> = searchResult.data.recipes
                val recipes: List<Recipe> = recipeListMapper.map(recipeDtoList)
                appendRecipesToCurrentList(recipes)
                RecipeListUiState.Success(currentRecipeList)
            }
        }
    }

    /**
     * Append new recipes to the current list of recipes
     */
    private fun appendRecipesToCurrentList(newRecipeList: List<Recipe>) {
        currentRecipeList.addAll(newRecipeList)
    }
}
