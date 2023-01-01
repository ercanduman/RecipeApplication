package ercanduman.recipeapplication.ui.recipe.list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ercanduman.recipeapplication.domain.usecase.SearchRecipeUseCase
import ercanduman.recipeapplication.ui.recipe.list.model.Category
import ercanduman.recipeapplication.ui.recipe.list.model.FoodCategory
import ercanduman.recipeapplication.ui.recipe.list.model.FoodCategoryProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val INITIAL_PAGE_ID = 1
private const val INITIAL_SEARCH_QUERY = "beef carrot"
private const val INITIAL_EMPTY_SEARCH_QUERY = ""
private const val TAG = "RecipeListViewModel"

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val searchRecipeUseCase: SearchRecipeUseCase,
    private val foodCategoryProvider: FoodCategoryProvider
) : ViewModel() {

    var recipeListUiState: MutableState<RecipeListUiState> = mutableStateOf(RecipeListUiState.Loading)
        private set

    var searchQuery: MutableState<String> = mutableStateOf(INITIAL_EMPTY_SEARCH_QUERY)
        private set

    var selectedCategory: MutableState<Category> = mutableStateOf(Category.NotProvided)
        private set

    init {
        searchRecipes()
    }

    fun searchRecipes() {
        val currentPageId: Int = getPageId()
        fetchRecipes(currentPageId, searchQuery.value)
    }

    // FIXME: Get current pageId and Update it based on scroll position.
    private fun getPageId(): Int {
        return INITIAL_PAGE_ID
    }

    private fun fetchRecipes(page: Int, searchQuery: String) {
        viewModelScope.launch {
            recipeListUiState.value = searchRecipeUseCase(
                page = page,
                searchQuery = searchQuery
            )
        }
    }

    fun getAllPredefinedFoodCategories(): List<FoodCategory> {
        return foodCategoryProvider.allPredefinedFoodCategories()
    }

    fun onCategoryClicked(categoryName: String) {
        val category: Category = foodCategoryProvider.getFoodCategory(categoryName)
        if (category is Category.Provided) {
            Log.d(TAG, "getFoodCategory: selected category is: ${category.foodCategory.value}")
            // Update selected category
            selectedCategory.value = category
        }

        // Update query and start a new search
        onQueryChanged(categoryName)
        searchRecipes()
    }

    fun onQueryChanged(newQuery: String) {
        searchQuery.value = newQuery
    }

    fun onRecipeClicked(recipeId: Int) {
        Log.d("TAG", "onRecipeClicked: clicked on recipe id:$recipeId")
        // FIXME: Navigate to RecipeDetailFragment
    }
}
