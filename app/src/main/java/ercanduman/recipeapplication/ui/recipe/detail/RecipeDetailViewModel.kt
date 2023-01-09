package ercanduman.recipeapplication.ui.recipe.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ercanduman.recipeapplication.domain.usecase.GetRecipeUseCase
import ercanduman.recipeapplication.ui.recipe.detail.model.RecipeDetailUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val getRecipeUseCase: GetRecipeUseCase
) : ViewModel() {
    var uiState: MutableState<RecipeDetailUiState> = mutableStateOf(RecipeDetailUiState.Loading)
        private set

    fun getRecipe(recipeId: Int) {
        viewModelScope.launch {
            uiState.value = getRecipeUseCase(recipeId)
        }
    }
}
