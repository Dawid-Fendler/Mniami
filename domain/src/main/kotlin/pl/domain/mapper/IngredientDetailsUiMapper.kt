package pl.domain.mapper

import pl.domain.model.ingredientdetails.EstimatedCost
import pl.domain.model.ingredientdetails.EstimatedCostUiModel
import pl.domain.model.ingredientdetails.IngredientDetails
import pl.domain.model.ingredientdetails.IngredientFullDetailsUiModel
import pl.domain.model.ingredientdetails.IngredientSubstitutes

fun mapToUiModel(
    ingredientDetails: IngredientDetails,
    ingredientsSubstitutes: IngredientSubstitutes
) = IngredientFullDetailsUiModel(
    name = ingredientDetails.name.orEmpty(),
    possibleUnits = ingredientDetails.possibleUnits.orEmpty(),
    amount = ingredientDetails.amount ?: 0.0,
    estimateCost = ingredientDetails.estimateCost?.toUiModel() ?: EstimatedCostUiModel(0.0, ""),
    consistency = ingredientDetails.consistency.orEmpty(),
    image = ingredientDetails.image.orEmpty(),
    categoryPath = ingredientDetails.categoryPath.orEmpty(),
    substitutes = ingredientsSubstitutes.substitutes.orEmpty()
)

fun EstimatedCost.toUiModel() = EstimatedCostUiModel(
    value = value,
    unit = unit
)
