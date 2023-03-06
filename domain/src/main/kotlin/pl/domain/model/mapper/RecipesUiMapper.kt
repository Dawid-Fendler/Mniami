package pl.domain.model.mapper

import pl.domain.model.recipes.RecipeInfo
import pl.domain.model.recipes.RecipeInfoUiModel

@SuppressWarnings("MagicNumber")
fun RecipeInfo.toUiModel() = RecipeInfoUiModel(
    recipeImage = recipeImage,
    recipeTitle = recipeTitle,
    recipeMinutes = "$recipeMinutes min",
    recipeScore = when (recipeScore) {
        in 0.0..10.0 -> 0.5
        in 11.0..20.0 -> 1.0
        in 21.0..30.0 -> 1.5
        in 31.0..40.0 -> 2.0
        in 41.0..50.0 -> 2.5
        in 51.0..60.0 -> 3.0
        in 61.0..70.0 -> 3.5
        in 71.0..80.0 -> 4.0
        in 81.0..90.0 -> 4.5
        in 91.0..100.0 -> 5.0
        else -> 0.0
    }.toFloat()
)
