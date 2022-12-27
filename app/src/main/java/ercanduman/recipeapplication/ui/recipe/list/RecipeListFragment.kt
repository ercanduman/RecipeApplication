package ercanduman.recipeapplication.ui.recipe.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ercanduman.recipeapplication.R
import ercanduman.recipeapplication.common.ui.theme.AppDimenDefaultDistance

private const val NAVIGATE_BUTTON_WIDTH = 180
private const val NAVIGATE_BUTTON_HEIGHT = 56
private const val NAVIGATE_BUTTON_TEXT = "Navigate to Details"

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    // Hilt - An activity or a fragment that is annotated with @AndroidEntryPoint can get the
    // ViewModel instance as normal using the by viewModels()
    // https://developer.android.com/training/dependency-injection/hilt-jetpack#kotlin
    private val viewModel: RecipeListViewModel by viewModels()

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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppDimenDefaultDistance),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = getString(R.string.label_recipe_list_fragment))

            Button(
                modifier = Modifier
                    .width(NAVIGATE_BUTTON_WIDTH.dp)
                    .height(NAVIGATE_BUTTON_HEIGHT.dp),
                onClick = { findNavController().navigate(R.id.action_navigate_to_recipeDetailFragment) }
            ) {
                Text(text = NAVIGATE_BUTTON_TEXT)
            }
        }
    }
}
