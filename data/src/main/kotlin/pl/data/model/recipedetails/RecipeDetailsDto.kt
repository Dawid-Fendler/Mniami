package pl.data.model.recipedetails

data class RecipeDetailsDto(
    val title: String,
    val image: String,
    val readyInMinutes: Int,
    val aggregateLikes: Int,
    val cheap: Boolean,
    val dairyFree: Boolean,
    val glutenFree: Boolean,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val dishTypes: List<String>,
    val summary: String,
    val winePairing: WinePairingDto,
    val extendedIngredients: List<ExtendedIngredientDto>
)
