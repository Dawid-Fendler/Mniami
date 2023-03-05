package pl.domain.repository

import pl.domain.model.recipes.Recipes
import pl.networking.model.NetworkResult

interface RecipesRepository {
    suspend fun getRecipes(): NetworkResult<Recipes>
}
