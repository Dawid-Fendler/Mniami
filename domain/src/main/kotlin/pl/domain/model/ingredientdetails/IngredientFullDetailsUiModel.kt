package pl.domain.model.ingredientdetails

data class IngredientFullDetailsUiModel(
    val name: String,
    val possibleUnits: List<String>,
    val amount: Double,
    val estimateCost: EstimatedCostUiModel,
    val consistency: String,
    val image: String,
    val categoryPath: List<String>,
    val substitutes: List<String>
)
