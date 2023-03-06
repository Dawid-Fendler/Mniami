package pl.recipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.architecture.SingleLiveEvent
import pl.coroutines.DispatcherProvider
import pl.domain.usecase.recipes.GetRecipesUseCase
import pl.domain.util.Resource
import pl.recipes.RecipesViewState.Loading
import pl.recipes.RecipesViewState.RecipesEmpty
import pl.recipes.RecipesViewState.RecipesLoadFailure
import pl.recipes.RecipesViewState.RecipesLoaded
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    var recipesViewState: MutableLiveData<RecipesViewState> = MutableLiveData()
        private set

    var progressLoadingEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
        private set

    fun start() {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch(dispatcher.io) {
            recipesViewState.postValue(Loading)
            progressLoadingEvent.postValue(true)
            when (val result = getRecipesUseCase.execute(Unit)) {
                is Resource.Success -> {
                    if (result.result.isNullOrEmpty()) {
                        recipesViewState.postValue(RecipesEmpty)
                        return@launch
                    }
                    recipesViewState.postValue(RecipesLoaded(result = result.result!!))
                    progressLoadingEvent.postValue(false)
                }
                is Resource.Failure ->
                    recipesViewState.postValue(RecipesLoadFailure(result.message))
            }
        }
    }
}
