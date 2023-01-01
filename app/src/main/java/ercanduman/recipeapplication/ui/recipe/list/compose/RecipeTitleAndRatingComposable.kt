package ercanduman.recipeapplication.ui.recipe.list.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import ercanduman.recipeapplication.common.ui.theme.AppText
import ercanduman.recipeapplication.common.ui.theme.AppTextSize20

@Composable
fun RecipeTitleAndRatingComposable(title: String, rating: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        AppText(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = AppTextSize20
        )

        AppText(
            text = rating,
            fontWeight = FontWeight.Bold
        )
    }
}
