package pl.data.mapper.ingredientdetails

import pl.data.model.ingredientdetails.EstimatedCostDto
import pl.data.model.ingredientdetails.IngredientDetailsDto
import pl.data.model.ingredientdetails.IngredientSubstitutesDto
import pl.domain.model.ingredientdetails.EstimatedCost
import pl.domain.model.ingredientdetails.IngredientDetails
import pl.domain.model.ingredientdetails.IngredientSubstitutes

fun IngredientDetailsDto.toDomain() = IngredientDetails(
    name = name,
    possibleUnits = possibleUnits,
    amount = amount,
    estimateCost = estimateCost?.toDomain(),
    consistency = consistency,
    image = image,
    categoryPath = categoryPath
)

fun EstimatedCostDto.toDomain() = EstimatedCost(
    value = value,
    unit = unit
)

fun IngredientSubstitutesDto.toDomain() = IngredientSubstitutes(
    substitutes = substitutes
)
