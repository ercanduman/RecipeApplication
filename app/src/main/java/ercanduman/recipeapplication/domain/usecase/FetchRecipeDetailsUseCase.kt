package ercanduman.recipeapplication.domain.usecase

import ercanduman.recipeapplication.data.repository.RecipeRepository
import ercanduman.recipeapplication.domain.mapper.RecipeDetailsMapper
import ercanduman.recipeapplication.ui.recipe.detail.model.RecipeDetailsUiState
import ercanduman.recipeapplication.util.RecipeResult
import javax.inject.Inject

class FetchRecipeDetailsUseCase @Inject constructor(
    private val repository: RecipeRepository,
    private val recipeDetailsMapper: RecipeDetailsMapper
) {
    suspend operator fun invoke(recipeId: Int): RecipeDetailsUiState {
        return when (val result = repository.fetchRecipeDetails(recipeId)) {
            RecipeResult.Loading -> RecipeDetailsUiState.Loading
            is RecipeResult.Error -> RecipeDetailsUiState.Error(result.message)
            is RecipeResult.Success -> recipeDetailsMapper.map(result.data)
        }
    }
}
