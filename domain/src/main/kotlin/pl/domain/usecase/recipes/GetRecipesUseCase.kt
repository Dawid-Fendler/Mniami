package pl.domain.usecase.recipes

import pl.domain.mapper.toUiModel
import pl.domain.model.recipes.RecipeInfoUiModel
import pl.domain.repository.RecipesRepository
import pl.domain.util.Resource
import pl.domain.util.UseCase
import pl.networking.model.NetworkResult
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository
) : UseCase<Unit, Resource<List<RecipeInfoUiModel>>> {

    override suspend fun execute(input: Unit): Resource<List<RecipeInfoUiModel>> {
        return when (val result = recipesRepository.getRecipes()) {
            is NetworkResult.Failure -> Resource.Failure(result.message.orEmpty())
            is NetworkResult.Success -> Resource.Success(result.data?.recipes?.map { it.toUiModel() })
        }
    }
}
