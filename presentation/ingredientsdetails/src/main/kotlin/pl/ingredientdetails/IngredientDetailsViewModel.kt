package pl.ingredientdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import pl.coroutines.DispatcherProvider
import pl.domain.mapper.mapToUiModel
import pl.domain.model.ingredientdetails.IngredientDetails
import pl.domain.model.ingredientdetails.IngredientSubstitutes
import pl.domain.usecase.ingredientdetails.GetIngredientDetailsUseCase
import pl.domain.usecase.ingredientdetails.GetIngredientSubstitutesUseCase
import pl.domain.util.Resource.Failure
import pl.domain.util.Resource.Success
import pl.ingredientdetails.IngredientDetailsViewState.IngredientDetailsLoadFailure
import pl.ingredientdetails.IngredientDetailsViewState.IngredientDetailsLoaded
import javax.inject.Inject

@HiltViewModel
class IngredientDetailsViewModel @Inject constructor(
    private val getIngredientDetailsUseCase: GetIngredientDetailsUseCase,
    private val getIngredientSubstitutesUseCase: GetIngredientSubstitutesUseCase,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    var ingredientDetailsViewState: MutableLiveData<IngredientDetailsViewState> = MutableLiveData()
        private set

    fun onStart(id: Int, name: String) {
        getIngredientDetails(id, name)
    }

    private fun getIngredientDetails(id: Int, name: String) {
        viewModelScope.launch(dispatcher.io) {
            getIngredientDetailsUseCase.execute(id).asFlow().zip(
                getIngredientSubstitutesUseCase.execute(name).asFlow()
            ) { result1, result2 ->
                when {
                    result1 is Success && result2 is Success -> {
                        if (result1.result == null || result2.result == null) {
                            ingredientDetailsViewState.postValue(IngredientDetailsLoadFailure(""))
                        }
                        ingredientDetailsViewState.postValue(
                            IngredientDetailsLoaded(
                                result = mapToUiModel(
                                    ingredientDetails = result1.result!! as IngredientDetails,
                                    ingredientsSubstitutes = result2.result!! as IngredientSubstitutes
                                )
                            )
                        )
                    }

                    result1 is Failure -> ingredientDetailsViewState.postValue(
                        IngredientDetailsLoadFailure(result1.message)
                    )

                    result2 is Failure -> ingredientDetailsViewState.postValue(
                        IngredientDetailsLoadFailure(result2.message)
                    )
                }
            }.collect()
        }
    }

    inline fun <reified T> T.asFlow(): Flow<T> = flow {
        emit(this@asFlow)
    }
}
