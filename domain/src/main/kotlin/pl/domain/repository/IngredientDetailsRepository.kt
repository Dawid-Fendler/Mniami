package pl.domain.repository

import pl.domain.model.ingredientdetails.IngredientDetails
import pl.domain.model.ingredientdetails.IngredientSubstitutes
import pl.networking.model.NetworkResult

interface IngredientDetailsRepository {
    suspend fun getIngredientDetails(id: Int): NetworkResult<IngredientDetails>
    suspend fun getIngredientSubstitutes(name: String): NetworkResult<IngredientSubstitutes>
}
