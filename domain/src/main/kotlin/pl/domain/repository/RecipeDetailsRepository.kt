package pl.domain.repository

import pl.domain.model.recipedetails.RecipeDetails
import pl.networking.model.NetworkResult

interface RecipeDetailsRepository {
    suspend fun getRecipeDetails(id: Int): NetworkResult<RecipeDetails>
}
