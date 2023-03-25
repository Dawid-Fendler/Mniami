package pl.data.repository

import pl.data.datasource.recipedetails.RecipeDetailsDataSource
import pl.data.mapper.recipedetails.toDomain
import pl.domain.model.recipedetails.RecipeDetails
import pl.domain.repository.RecipeDetailsRepository
import pl.networking.extensions.callWithHandler
import pl.networking.model.NetworkResult
import javax.inject.Inject

class RecipeDetailsRepositoryImpl @Inject constructor(
    private val dataSource: RecipeDetailsDataSource
) : RecipeDetailsRepository {

    override suspend fun getRecipeDetails(id: Int): NetworkResult<RecipeDetails> {
        return callWithHandler { dataSource.getRecipeDetails(id).toDomain() }
    }
}
