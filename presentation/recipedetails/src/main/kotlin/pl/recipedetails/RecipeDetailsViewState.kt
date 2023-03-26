package pl.recipedetails

import pl.domain.model.recipedetails.RecipeDetailsUiModel

sealed class RecipeDetailsViewState {
    data class RecipeDetailsLoaded(val result: RecipeDetailsUiModel) : RecipeDetailsViewState()
    data class RecipeDetailsLoadFailure(val message: String) : RecipeDetailsViewState()
    object Loading : RecipeDetailsViewState()
}
