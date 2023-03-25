package pl.data.datasource.recipes

import pl.data.model.recipes.RecipesDto
import pl.data.service.RecipesApi
import javax.inject.Inject

internal class RecipesRemoteDataSource @Inject constructor(
    private val api: RecipesApi
) : RecipesDataSource {

    override suspend fun getRecipes(): RecipesDto {
        return api.getRecipes()
    }
}
