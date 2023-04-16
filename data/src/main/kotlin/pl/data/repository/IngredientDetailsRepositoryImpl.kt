package pl.data.repository

import pl.data.datasource.ingredientdetails.IngredientsDetailsDataSource
import pl.data.mapper.ingredientdetails.toDomain
import pl.domain.model.ingredientdetails.IngredientDetails
import pl.domain.model.ingredientdetails.IngredientSubstitutes
import pl.domain.repository.IngredientDetailsRepository
import pl.networking.extensions.callWithHandler
import pl.networking.model.NetworkResult
import javax.inject.Inject

class IngredientDetailsRepositoryImpl @Inject constructor(
    private val dataSource: IngredientsDetailsDataSource
) : IngredientDetailsRepository {
    override suspend fun getIngredientDetails(id: Int): NetworkResult<IngredientDetails> {
        return callWithHandler { dataSource.getIngredientDetails(id).toDomain() }
    }

    override suspend fun getIngredientSubstitutes(name: String): NetworkResult<IngredientSubstitutes> {
        return callWithHandler { dataSource.getIngredientSubstitutes(name).toDomain() }
    }
}
