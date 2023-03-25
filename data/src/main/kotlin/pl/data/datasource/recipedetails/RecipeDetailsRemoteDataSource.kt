package pl.data.datasource.recipedetails

import pl.data.model.recipedetails.RecipeDetailsDto
import pl.data.service.RecipeDetailsApi
import javax.inject.Inject

class RecipeDetailsRemoteDataSource @Inject constructor(
    private val api: RecipeDetailsApi
) : RecipeDetailsDataSource {

    override suspend fun getRecipeDetails(id: Int): RecipeDetailsDto {
        return api.getRecipeDetails(id)
    }
}
