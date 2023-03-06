package pl.data.mapper.recipes

import pl.data.model.recipes.RecipeInfoDto
import pl.data.model.recipes.RecipesDto
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
