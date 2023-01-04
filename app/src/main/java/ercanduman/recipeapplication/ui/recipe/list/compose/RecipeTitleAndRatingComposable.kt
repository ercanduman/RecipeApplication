package ercanduman.recipeapplication.ui.recipe.list.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import ercanduman.recipeapplication.ui.common.theme.AppDimenDefaultDistance
import ercanduman.recipeapplication.ui.common.theme.AppText
import ercanduman.recipeapplication.ui.common.theme.AppTextSize20

private const val TITLE_MAX_LINES = 1
private const val TITLE_WEIGHT = 1f

@Composable
fun RecipeTitleAndRatingComposable(
    title: String,
    rating: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        AppText(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = AppTextSize20,
            maxLines = TITLE_MAX_LINES,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(TITLE_WEIGHT)
        )

        AppText(
            text = rating,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = AppDimenDefaultDistance)
        )
    }
}
