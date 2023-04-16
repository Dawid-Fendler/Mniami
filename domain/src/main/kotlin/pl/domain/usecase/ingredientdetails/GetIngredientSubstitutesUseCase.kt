package pl.domain.usecase.ingredientdetails

import pl.domain.model.ingredientdetails.IngredientSubstitutes
import pl.domain.repository.IngredientDetailsRepository
import pl.domain.util.Resource
import pl.domain.util.UseCase
import pl.networking.model.NetworkResult
import javax.inject.Inject

class GetIngredientSubstitutesUseCase @Inject constructor(
    private val ingredientDetailsRepository: IngredientDetailsRepository
) : UseCase<String, Resource<IngredientSubstitutes>> {

    override suspend fun execute(input: String): Resource<IngredientSubstitutes> {
        return when (val result = ingredientDetailsRepository.getIngredientSubstitutes(input)) {
            is NetworkResult.Failure -> Resource.Failure(result.message.orEmpty())
            is NetworkResult.Success -> Resource.Success(result.data)
        }
    }
}
