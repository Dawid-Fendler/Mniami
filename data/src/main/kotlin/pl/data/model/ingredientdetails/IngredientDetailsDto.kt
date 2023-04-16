package pl.data.model.ingredientdetails

data class IngredientDetailsDto(
    val name: String?,
    val possibleUnits: List<String>?,
    val amount: Double?,
    val estimateCost: EstimatedCostDto?,
    val consistency: String?,
    val image: String?,
    val categoryPath: List<String>?
)
