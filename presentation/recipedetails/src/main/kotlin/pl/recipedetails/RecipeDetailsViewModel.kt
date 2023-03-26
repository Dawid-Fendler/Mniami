package pl.recipedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.coroutines.DispatcherProvider
import pl.domain.usecase.recipedetails.GetRecipeDetailsUseCase
import pl.domain.util.Resource
import pl.recipedetails.RecipeDetailsViewState.Loading
import pl.recipedetails.RecipeDetailsViewState.RecipeDetailsLoadFailure
import pl.recipedetails.RecipeDetailsViewState.RecipeDetailsLoaded
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    var recipeDetailsViewState: MutableLiveData<RecipeDetailsViewState> = MutableLiveData()
        private set

    fun onStart(id: Int) {
        getRecipeDetails(id)
    }

    private fun getRecipeDetails(id: Int) {
        viewModelScope.launch(dispatcher.io) {
            recipeDetailsViewState.postValue(Loading)
            when (val result = getRecipeDetailsUseCase.execute(id)) {
                is Resource.Success -> {
                    if (result.result == null) {
                        recipeDetailsViewState.postValue(RecipeDetailsLoadFailure(""))
                        return@launch
                    }
                    recipeDetailsViewState.postValue(RecipeDetailsLoaded(result = result.result!!))
                }
                is Resource.Failure ->
                    recipeDetailsViewState.postValue(RecipeDetailsLoadFailure(result.message))
            }
        }
    }
}
