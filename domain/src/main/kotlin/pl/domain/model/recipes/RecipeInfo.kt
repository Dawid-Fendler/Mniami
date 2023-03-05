package pl.domain.model.recipes

data class RecipeInfo(
    val recipeId: Int,
    val recipeImage: String,
    val recipeTitle: String,
    val recipeMinutes: Int,
    val recipeScore: Double
)
