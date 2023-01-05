package ercanduman.recipeapplication.domain.usecase

import ercanduman.recipeapplication.common.util.RecipeResult
import ercanduman.recipeapplication.data.repository.RecipeRepository
import ercanduman.recipeapplication.domain.mapper.RecipeListMapper
import ercanduman.recipeapplication.ui.recipe.list.RecipeListUiState
import javax.inject.Inject

class SearchRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository,
    private val recipeListMapper: RecipeListMapper
) {
    suspend operator fun invoke(
        page: Int,
        searchQuery: String
    ): RecipeListUiState {
        return when (val searchResult = repository.searchRecipes(page, searchQuery)) {
            RecipeResult.Loading -> {
                RecipeListUiState.Loading
            }

            is RecipeResult.Error -> {
                val errorMessage = searchResult.message
                RecipeListUiState.Error(errorMessage)
            }

            is RecipeResult.Success -> {
                val recipeDtoList = searchResult.data.recipes
                val recipes = recipeListMapper.map(recipeDtoList)
                RecipeListUiState.Success(recipes)
            }
        }
    }
}
