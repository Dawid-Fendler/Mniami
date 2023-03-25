package pl.domain.model.recipedetails

data class ExtendedIngredient(
    val id: Int,
    val image: String,
    val name: String,
    val unit: String,
    val amount: Double
)
