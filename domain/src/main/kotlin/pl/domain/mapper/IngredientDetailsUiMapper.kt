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
    name = ingredientDetails.name,
    possibleUnits = ingredientDetails.possibleUnits,
    amount = ingredientDetails.amount,
    estimateCost = ingredientDetails.estimateCost?.toUiModel(),
    consistency = ingredientDetails.consistency,
    image = ingredientDetails.image,
    categoryPath = ingredientDetails.categoryPath,
    substitutes = ingredientsSubstitutes.substitutes
)

fun EstimatedCost.toUiModel() = EstimatedCostUiModel(
    value = value,
    unit = unit
)
