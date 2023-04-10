package pl.data.mapper.recipedetails

import pl.data.model.recipedetails.ExtendedIngredientDto
import pl.data.model.recipedetails.RecipeDetailsDto
import pl.data.model.recipedetails.WinePairingDto
import pl.domain.model.recipedetails.ExtendedIngredient
import pl.domain.model.recipedetails.RecipeDetails
import pl.domain.model.recipedetails.WinePairing

fun RecipeDetailsDto.toDomain() = RecipeDetails(
    title = title,
    image = image,
    minutes = readyInMinutes,
    likes = aggregateLikes,
    dairyFree = dairyFree,
    cheap = cheap,
    glutenFree = glutenFree,
    vegan = vegan,
    vegetarian = vegetarian,
    dishTypes = dishTypes,
    summary = summary,
    winePairing = winePairing.toDomain(),
    ingredients = extendedIngredients.map { it.toDomain() },
    healthScore = healthScore
)

fun WinePairingDto.toDomain() = WinePairing(
    wines = pairedWines
)

fun ExtendedIngredientDto.toDomain() = ExtendedIngredient(
    id = id,
    image = image
)
