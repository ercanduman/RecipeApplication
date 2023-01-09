package ercanduman.recipeapplication.ui.recipe.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

const val KEY_RECIPE_ID = "RecipeDetailFragment.recipeId"
const val INVALID_RECIPE_ID = -1

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {

    private val viewModel: RecipeDetailViewModel by viewModels()

    private var recipeId: Int = INVALID_RECIPE_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipeId = arguments?.getInt(KEY_RECIPE_ID, INVALID_RECIPE_ID) ?: INVALID_RECIPE_ID
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent { FragmentContent() }
        }
    }

    @Composable
    private fun FragmentContent() {
        if (recipeId == INVALID_RECIPE_ID) {
            Text(text = "Recipe Detail fragment. Invalid Recipe Id!")
        } else {
            Text(text = "Recipe Detail fragment. recipeId=$recipeId")
//            viewModel.getRecipe(recipeId)
        }
    }
}
