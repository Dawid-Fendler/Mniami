package pl.recipes

import pl.domain.model.recipes.RecipeInfoUiModel

sealed class RecipesViewState {
    data class RecipesLoaded(val result: List<RecipeInfoUiModel>) : RecipesViewState()
    data class RecipesLoadFailure(val message: String) : RecipesViewState()
    object RecipesEmpty : RecipesViewState()
    object Loading : RecipesViewState()
}
