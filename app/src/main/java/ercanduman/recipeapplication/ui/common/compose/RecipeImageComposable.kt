package ercanduman.recipeapplication.ui.common.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ercanduman.recipeapplication.R
import ercanduman.recipeapplication.ui.recipe.list.DEFAULT_CONTENT_DESCRIPTION

const val RECIPE_IMAGE_HEIGHT: Int = 256

@Composable
fun RecipeImageComposable(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = DEFAULT_CONTENT_DESCRIPTION,
        modifier = Modifier
            .fillMaxWidth()
            .height(RECIPE_IMAGE_HEIGHT.dp),
        placeholder = painterResource(id = R.drawable.recipe_placeholder_image),
        contentScale = ContentScale.Crop
    )
}
