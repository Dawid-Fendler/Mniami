package pl.data.datasource.recipedetails

import pl.data.model.recipedetails.RecipeDetailsDto

interface RecipeDetailsDataSource {
    suspend fun getRecipeDetails(id: Int): RecipeDetailsDto
}
