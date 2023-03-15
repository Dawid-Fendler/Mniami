package pl.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.coroutines.DispatcherProvider
import pl.domain.usecase.onboarding.GetIsLoggedUseCase
import pl.domain.usecase.onboarding.GetOnboardingDisplayedUseCase
import pl.domain.usecase.onboarding.SaveOnboardingDisplayedUseCase
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getOnboardingDisplayedUseCase: GetOnboardingDisplayedUseCase,
    private val saveOnboardingDisplayedUseCase: SaveOnboardingDisplayedUseCase,
    private val getIsLoggedUseCase: GetIsLoggedUseCase,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    var onboardingDisplayed: MutableLiveData<Boolean> = MutableLiveData()
        private set

    var isLogged: MutableLiveData<Boolean> = MutableLiveData()
        private set

    fun saveOnboardingDisplayed() {
        viewModelScope.launch(dispatcher.io) {
            saveOnboardingDisplayedUseCase.execute(Unit)
        }
    }

    fun getOnboardingDisplayed() {
        viewModelScope.launch(dispatcher.io) {
            getOnboardingDisplayedUseCase.execute(Unit).collect {
                onboardingDisplayed.postValue(it)
            }
        }
    }

    fun getIsLogged() {
        viewModelScope.launch(dispatcher.io) {
            getIsLoggedUseCase.execute(Unit).collect {
                isLogged.postValue(it)
            }
        }
    }
}
