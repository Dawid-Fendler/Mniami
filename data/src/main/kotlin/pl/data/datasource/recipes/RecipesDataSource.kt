package pl.data.datasource.recipes

import pl.data.model.recipes.RecipesDto

interface RecipesDataSource {
    suspend fun getRecipes(): RecipesDto
}
