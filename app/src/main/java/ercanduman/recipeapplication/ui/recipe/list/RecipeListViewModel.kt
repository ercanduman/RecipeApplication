package ercanduman.recipeapplication.ui.recipe.list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ercanduman.recipeapplication.BuildConfig
import ercanduman.recipeapplication.domain.usecase.SearchRecipeUseCase
import ercanduman.recipeapplication.ui.recipe.list.model.Category
import ercanduman.recipeapplication.ui.recipe.list.model.FoodCategory
import ercanduman.recipeapplication.ui.recipe.list.model.FoodCategoryProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val INITIAL_POSITION = 0
private const val INITIAL_EMPTY_SEARCH_QUERY = ""
private const val TAG = "RecipeListViewModel"

// Pagination related constants, also match values in the API.
private const val PAGING_PAGE_SIZE = 30
private const val PAGING_INITIAL_PAGE = 1
private const val PAGING_INCREMENT_SIZE = 1

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

    var selectedCategoryPosition: Int = INITIAL_POSITION
        private set

    private val currentPage = mutableStateOf(PAGING_INITIAL_PAGE)

    private var recipeListScrollPosition = INITIAL_POSITION

    init {
        executeNewSearch()
    }

    fun executeNewSearch() {
        fetchRecipes()
    }

    private fun executeNextPageSearch() {
        incrementPageSize()
        fetchRecipes()
    }

    private fun resetSearchState() {
        recipeListUiState.value = RecipeListUiState.Loading
        currentPage.value = PAGING_INITIAL_PAGE
        recipeListScrollPosition = INITIAL_POSITION
        searchRecipeUseCase.clearCurrentRecipeList()
    }

    private fun incrementPageSize() {
        currentPage.value = currentPage.value + PAGING_INCREMENT_SIZE
    }

    fun onRecipeListScrollPositionChanged(position: Int) {
        recipeListScrollPosition = position
        Log.d(TAG, "onRecipeListScrollPositionChanged: position:$recipeListScrollPosition")
        Log.d(
            TAG,
            "onRecipeListScrollPositionChanged: hasReachedTheEndOfTheList:${hasReachedTheEndOfTheList()}"
        )
        // Request next page items if the $recipeListScrollPosition has reached the end of the list and
        // UiState is not currently loading
        if (hasReachedTheEndOfTheList() && !isUiStateLoading()) {
            executeNextPageSearch()
        }
    }

    /*
    Prevent duplicate events due to recompose happening quickly
    Ex: If $currentPage=1, the fetched item size is currentPage*PAGE_SIZE -> 1*30=30. Next page items
    should be fetched only if the $recipeListScrollPosition has reached the end of the list
    */
    private fun hasReachedTheEndOfTheList(): Boolean {
        return recipeListScrollPosition + PAGING_INCREMENT_SIZE >= currentPage.value * PAGING_PAGE_SIZE
    }

    private fun isUiStateLoading(): Boolean {
        return recipeListUiState.value is RecipeListUiState.Loading
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            recipeListUiState.value = searchRecipeUseCase(
                page = currentPage.value,
                searchQuery = searchQuery.value
            )
        }
    }

    fun getAllPredefinedFoodCategories(): List<FoodCategory> {
        return foodCategoryProvider.allPredefinedFoodCategories()
    }

    fun onQueryChanged(newQuery: String) {
        resetSearchState()
        searchQuery.value = newQuery
        selectedCategory.value = foodCategoryProvider.getFoodCategory(newQuery)
        executeNewSearch()
    }

    fun onCategoryPositionChanged(position: Int) {
        selectedCategoryPosition = position
    }

    fun onRecipeClicked(recipeId: Int) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onRecipeClicked: clicked on recipe id:$recipeId")
//        recipeListUiState.value = RecipeListUiState.DisplayRecipeDetails(recipeId)
    }
}
