package pl.data.datasource.ingredientdetails

import pl.data.model.ingredientdetails.IngredientDetailsDto
import pl.data.model.ingredientdetails.IngredientSubstitutesDto

interface IngredientsDetailsDataSource {
    suspend fun getIngredientDetails(id: Int): IngredientDetailsDto
    suspend fun getIngredientSubstitutes(name: String): IngredientSubstitutesDto
}
