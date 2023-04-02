package pl.domain.model.recipedetails

data class RecipeDetails(
    val title: String,
    val image: String,
    val minutes: Int,
    val likes: Int,
    val cheap: Boolean,
    val dairyFree: Boolean,
    val glutenFree: Boolean,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val dishTypes: List<String>,
    val summary: String,
    val winePairing: WinePairing,
    val ingredients: List<ExtendedIngredient>,
    val healthScore: Double
)
