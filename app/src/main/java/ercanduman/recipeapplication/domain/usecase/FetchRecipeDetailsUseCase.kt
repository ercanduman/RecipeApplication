package ercanduman.recipeapplication.domain.usecase

import ercanduman.recipeapplication.data.repository.RecipeRepository
import ercanduman.recipeapplication.domain.mapper.RecipeDetailMapper
import ercanduman.recipeapplication.ui.recipe.detail.model.RecipeDetailUiState
import ercanduman.recipeapplication.util.RecipeResult
import javax.inject.Inject

class FetchRecipeDetailsUseCase @Inject constructor(
    private val repository: RecipeRepository,
    private val recipeDetailMapper: RecipeDetailMapper
) {
    suspend operator fun invoke(recipeId: Int): RecipeDetailUiState {
        return when (val result = repository.fetchRecipeDetails(recipeId)) {
            RecipeResult.Loading -> RecipeDetailUiState.Loading
            is RecipeResult.Error -> RecipeDetailUiState.Error(result.message)
            is RecipeResult.Success -> recipeDetailMapper.map(result.data)
        }
    }
}
