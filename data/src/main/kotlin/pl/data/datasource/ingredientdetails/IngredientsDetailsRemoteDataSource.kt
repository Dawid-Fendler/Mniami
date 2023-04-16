package pl.data.datasource.ingredientdetails

import pl.data.model.ingredientdetails.IngredientDetailsDto
import pl.data.model.ingredientdetails.IngredientSubstitutesDto
import pl.data.service.IngredientsDetailsApi
import javax.inject.Inject

class IngredientsDetailsRemoteDataSource @Inject constructor(
    private val api: IngredientsDetailsApi
) : IngredientsDetailsDataSource {

    override suspend fun getIngredientDetails(id: Int): IngredientDetailsDto {
        return api.getIngredientDetails(id)
    }

    override suspend fun getIngredientSubstitutes(name: String): IngredientSubstitutesDto {
        return api.getIngredientSubstitutes(name)
    }
}
