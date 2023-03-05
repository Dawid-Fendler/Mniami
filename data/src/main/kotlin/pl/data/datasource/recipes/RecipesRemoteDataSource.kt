package pl.data.datasource.recipes

import javax.inject.Inject

internal class RecipesRemoteDataSource @Inject constructor(
    private val api: pl.data.service.RecipesApi
) : RecipesDataSource {

    override suspend fun getRecipes(): pl.data.model.recipes.RecipesDto {
        return api.getRecipes()
    }
}
