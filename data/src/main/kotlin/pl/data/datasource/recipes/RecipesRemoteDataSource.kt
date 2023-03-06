package pl.data.datasource.recipes

import pl.data.model.recipes.RecipesDto
import javax.inject.Inject

internal class RecipesRemoteDataSource @Inject constructor(
    private val api: pl.data.service.RecipesApi
) : RecipesDataSource {

    override suspend fun getRecipes(): RecipesDto {
        return api.getRecipes()
    }
}
