package ercanduman.recipeapplication.ui.recipe.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ercanduman.recipeapplication.domain.usecase.SearchRecipeUseCase
import ercanduman.recipeapplication.ui.recipe.detail.INVALID_RECIPE_ID
import ercanduman.recipeapplication.ui.recipe.list.model.Category
import ercanduman.recipeapplication.ui.recipe.list.model.FoodCategory
import ercanduman.recipeapplication.ui.recipe.list.model.FoodCategoryProvider
import ercanduman.recipeapplication.ui.recipe.list.model.RecipeListUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val INITIAL_POSITION = 0
private const val INITIAL_SEARCH_QUERY = ""
private const val DELAY_API_CALL_MILLIS = 1000L

// Pagination related constants, also match values in the API.
private const val PAGING_PAGE_SIZE = 30
private const val PAGING_INITIAL_PAGE = 1
private const val PAGING_INCREMENT_RANGE = 1

private const val PAGING_NEXT_PAGE_INTERVAL = 4

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val searchRecipeUseCase: SearchRecipeUseCase,
    private val foodCategoryProvider: FoodCategoryProvider
) : ViewModel() {

    var recipeListUiState: RecipeListUiState by mutableStateOf(RecipeListUiState())
        private set

    var searchQuery: MutableState<String> = mutableStateOf(INITIAL_SEARCH_QUERY)
        private set

    var selectedCategory: MutableState<Category> = mutableStateOf(Category.NotProvided)
        private set

    var selectedCategoryPosition: Int = INITIAL_POSITION
        private set

    private val currentPage: MutableState<Int> = mutableStateOf(PAGING_INITIAL_PAGE)

    private var recipeListScrollPosition: Int = INITIAL_POSITION

    private var fetchRecipesJob: Job? = null

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
        cancelRunningJob()
        recipeListUiState = RecipeListUiState()
        currentPage.value = PAGING_INITIAL_PAGE
        recipeListScrollPosition = INITIAL_POSITION
        searchRecipeUseCase.clearCurrentRecipeList()
    }

    /**
     * Cancels running job in order to prevent unnecessary network calls.
     */
    private fun cancelRunningJob() {
        fetchRecipesJob?.let { job ->
            job.cancel()
            fetchRecipesJob = null
        }
    }

    private fun incrementPageSize() {
        currentPage.value = currentPage.value + PAGING_INCREMENT_RANGE
    }

    fun onRecipeListScrollPositionChanged(position: Int) {
        recipeListScrollPosition = position

        // Request next page items if the $recipeListScrollPosition has reached to the end of the list and
        // UiState is not currently loading
        if (isEligibleToMakeNextPageSearch() && !recipeListUiState.isLoading) {
            executeNextPageSearch()
        }
    }

    /**
     Prevent duplicate events due to recompose happening quickly
     Ex: If $currentPage=1, the fetched item size is currentPage*PAGE_SIZE -> 1*30=30. Next page items
     should be fetched only if the $recipeListScrollPosition has reached the last $PAGING_NEXT_PAGE_INTERVAL
     elements at the end of the list.

     i.e: PAGING_NEXT_PAGE_INTERVAL=4, the next page should be loaded before the user reaches the last 4
     items. So by the time the user reaches the end, the new items are already loaded and there will be a
     smooth transition.
     */
    private fun isEligibleToMakeNextPageSearch(): Boolean {
        return recipeListScrollPosition + PAGING_NEXT_PAGE_INTERVAL >= currentPage.value * PAGING_PAGE_SIZE
    }

    private fun fetchRecipes() {
        fetchRecipesJob = viewModelScope.launch {
            delayApiCall()
            recipeListUiState = searchRecipeUseCase(
                page = currentPage.value,
                searchQuery = searchQuery.value
            )
        }
    }

    /**
     * This function delays the API call until the user input is finished, so that no unnecessary network
     * calls are made for each character typed in the text box.
     */
    private suspend fun delayApiCall() {
        delay(timeMillis = DELAY_API_CALL_MILLIS)
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
        recipeListUiState = recipeListUiState.copy(recipeId = recipeId)
    }

    fun navigationProcessed() {
        recipeListUiState = recipeListUiState.copy(recipeId = INVALID_RECIPE_ID)
    }
}
