package pl.data.model.recipedetails

data class ExtendedIngredientDto(
    val id: Int,
    val image: String,
    val name: String,
    val unit: String,
    val amount: Double
)
