package pl.domain.usecase.recipes

import pl.domain.model.recipes.Recipes
import pl.domain.repository.RecipesRepository
import pl.networking.model.NetworkResult
import pl.domain.util.Resource
import pl.domain.util.UseCase
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository
) : UseCase<Unit, Resource<Recipes>> {

    override suspend fun execute(input: Unit): Resource<Recipes> {
        return when (val result = recipesRepository.getRecipes()) {
            is NetworkResult.Failure -> Resource.Failure(result.message.orEmpty())
            is NetworkResult.Success -> Resource.Success(result.data)
        }
    }
}
