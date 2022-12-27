package ercanduman.recipeapplication.ui.recipe.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ercanduman.recipeapplication.domain.usecase.GetRecipeUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val getRecipeUseCase: GetRecipeUseCase
) : ViewModel() {

    fun getRecipe(recipeId: Int) {
        viewModelScope.launch {
            getRecipeUseCase(recipeId)
        }
    }
}
