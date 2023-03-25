package pl.domain.usecase.recipedetails

import pl.domain.mapper.toUiModel
import pl.domain.model.recipedetails.RecipeDetailsUiModel
import pl.domain.repository.RecipeDetailsRepository
import pl.domain.util.Resource
import pl.domain.util.UseCase
import pl.networking.model.NetworkResult
import javax.inject.Inject

class GetRecipeDetailsUseCase @Inject constructor(
    private val recipeDetailsRepository: RecipeDetailsRepository
) : UseCase<Int, Resource<RecipeDetailsUiModel>> {

    override suspend fun execute(input: Int): Resource<RecipeDetailsUiModel> {
        return when (val result = recipeDetailsRepository.getRecipeDetails(input)) {
            is NetworkResult.Failure -> Resource.Failure(result.message.orEmpty())
            is NetworkResult.Success -> Resource.Success(result.data?.toUiModel())
        }
    }
}
