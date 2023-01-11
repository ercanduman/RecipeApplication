package ercanduman.recipeapplication.ui.recipe.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ercanduman.recipeapplication.R
import ercanduman.recipeapplication.domain.usecase.FetchRecipeDetailsUseCase
import ercanduman.recipeapplication.ui.recipe.detail.model.RecipeDetailUiState
import ercanduman.recipeapplication.util.AppResourcesProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val fetchRecipeDetailsUseCase: FetchRecipeDetailsUseCase,
    private val appResourcesProvider: AppResourcesProvider
) : ViewModel() {
    var recipeDetailUiState: MutableState<RecipeDetailUiState> = mutableStateOf(RecipeDetailUiState.Loading)
        private set

    fun fetchRecipeDetails(recipeId: Int) {
        viewModelScope.launch {
            if (recipeId == INVALID_RECIPE_ID) {
                val errorMessage: String = appResourcesProvider.getString(
                    resourceId = R.string.error_invalid_recipe_id,
                    substitutingValue = "$recipeId"
                )
                recipeDetailUiState.value = RecipeDetailUiState.Error(errorMessage)
            } else {
                recipeDetailUiState.value = fetchRecipeDetailsUseCase(recipeId)
            }
        }
    }
}
