package pl.domain.model.ingredientdetails

data class IngredientDetails(
    val name: String?,
    val possibleUnits: List<String>?,
    val amount: Double?,
    val estimateCost: EstimatedCost?,
    val consistency: String?,
    val image: String?,
    val categoryPath: List<String>?
)
