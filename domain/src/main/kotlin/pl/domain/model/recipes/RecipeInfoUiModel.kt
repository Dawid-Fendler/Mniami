package pl.domain.model.recipes

data class RecipeInfoUiModel(
    val id: Int,
    val recipeImage: String,
    val recipeTitle: String,
    val recipeMinutes: String,
    val recipeScore: Float
)
