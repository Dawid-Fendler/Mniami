package pl.data.repository

import pl.data.datasource.recipes.RecipesDataSource
import pl.data.model.recipes.toDomain
import pl.domain.model.recipes.Recipes
import pl.domain.repository.RecipesRepository
import pl.networking.extensions.callWithHandler
import pl.networking.model.NetworkResult
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val dataSource: RecipesDataSource
) : RecipesRepository {

    override suspend fun getRecipes(): NetworkResult<Recipes> {
        return callWithHandler { dataSource.getRecipes().toDomain() }
    }
}
