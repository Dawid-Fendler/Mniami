package pl.data.model.recipes

import pl.domain.model.recipes.RecipeInfo
import pl.domain.model.recipes.Recipes

fun RecipesDto.toDomain() = Recipes(
    recipes = recipes.map { it.toDomain() }
)

fun RecipeInfoDto.toDomain() = RecipeInfo(
    recipeId = id,
    recipeImage = image,
    recipeTitle = title,
    recipeMinutes = readyInMinutes,
    recipeScore = healthScore
)
