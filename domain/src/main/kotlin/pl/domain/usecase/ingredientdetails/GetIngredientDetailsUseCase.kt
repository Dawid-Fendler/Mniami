package pl.domain.usecase.ingredientdetails

import pl.domain.model.ingredientdetails.IngredientDetails
import pl.domain.repository.IngredientDetailsRepository
import pl.domain.util.Resource
import pl.domain.util.UseCase
import pl.networking.model.NetworkResult
import javax.inject.Inject

class GetIngredientDetailsUseCase @Inject constructor(
    private val ingredientDetailsRepository: IngredientDetailsRepository
) : UseCase<Int, Resource<IngredientDetails>> {

    override suspend fun execute(input: Int): Resource<IngredientDetails> {
        return when (val result = ingredientDetailsRepository.getIngredientDetails(input)) {
            is NetworkResult.Failure -> Resource.Failure(result.message.orEmpty())
            is NetworkResult.Success -> Resource.Success(result.data)
        }
    }
}
