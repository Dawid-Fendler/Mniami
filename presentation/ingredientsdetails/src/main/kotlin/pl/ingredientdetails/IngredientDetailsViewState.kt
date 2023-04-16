package pl.ingredientdetails

import pl.domain.model.ingredientdetails.IngredientFullDetailsUiModel

sealed class IngredientDetailsViewState {
    data class IngredientDetailsLoaded(val result: IngredientFullDetailsUiModel) :
        IngredientDetailsViewState()

    data class IngredientDetailsLoadFailure(val message: String) : IngredientDetailsViewState()
    object Loading : IngredientDetailsViewState()
}
