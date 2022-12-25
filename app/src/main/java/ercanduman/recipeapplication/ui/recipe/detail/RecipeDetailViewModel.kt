package ercanduman.recipeapplication.ui.recipe.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ercanduman.recipeapplication.domain.usecase.GetRecipeUseCase
import kotlinx.coroutines.launch

class RecipeDetailViewModel(
    private val getRecipeUseCase: GetRecipeUseCase
) : ViewModel() {

    fun getRecipe(recipeId: Int) {
        viewModelScope.launch {
            getRecipeUseCase(recipeId)
        }
    }
}
