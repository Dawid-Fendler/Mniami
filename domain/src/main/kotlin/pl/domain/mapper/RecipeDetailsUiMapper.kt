package pl.domain.mapper

import pl.domain.model.recipedetails.ExtendedIngredient
import pl.domain.model.recipedetails.ExtendedIngredientUiModel
import pl.domain.model.recipedetails.RecipeDetails
import pl.domain.model.recipedetails.RecipeDetailsUiModel
import pl.domain.model.recipedetails.WinePairing
import pl.domain.model.recipedetails.WinePairingUiModel

fun RecipeDetails.toUiModel() = RecipeDetailsUiModel(
    title = title,
    image = image,
    minutes = minutes,
    likes = likes,
    summary = summary,
    winePairing = winePairing.toUiModel(),
    dishTypes = dishTypes,
    dietTypes = createDietTypesList(vegan, vegetarian, glutenFree, cheap, dairyFree),
    ingredients = ingredients.map { it.toUiModel() },
    healthScore = when (healthScore) {
        in 0.0..10.0 -> 0.5
        in 11.0..20.0 -> 1.0
        in 21.0..30.0 -> 1.5
        in 31.0..40.0 -> 2.0
        in 41.0..50.0 -> 2.5
        in 51.0..60.0 -> 3.0
        in 61.0..70.0 -> 3.5
        in 71.0..80.0 -> 4.0
        in 81.0..90.0 -> 4.5
        in 91.0..100.0 -> 5.0
        else -> 0.0
    }.toFloat()
)

fun WinePairing.toUiModel() = WinePairingUiModel(
    wines = wines
)

private fun createDietTypesList(
    vegan: Boolean,
    vegetarian: Boolean,
    glutenFree: Boolean,
    cheap: Boolean,
    dairyFree: Boolean
): List<String> {
    val dietTypes: List<Pair<String, Boolean>> = listOf(
        Pair("Vegan", vegan),
        Pair("Vegetarian", vegetarian),
        Pair("Gluten Free", glutenFree),
        Pair("Cheap", cheap),
        Pair("Dairy Free", dairyFree)
    )

    return mutableListOf<String>().apply {
        dietTypes.indices
            .asSequence()
            .filter { dietTypes[it].second }
            .forEach { add(dietTypes[it].first) }
    }
}

fun ExtendedIngredient.toUiModel() = ExtendedIngredientUiModel(
    id = id,
    image = image,
    name = name
)
