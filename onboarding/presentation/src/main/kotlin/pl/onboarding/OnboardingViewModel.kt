package pl.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import pl.domain.usecase.GetOnboardingDisplayedUseCase
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    private val getOnboardingDisplayedUseCase: GetOnboardingDisplayedUseCase,
    private val saveOnboardingDisplayedUseCase: GetOnboardingDisplayedUseCase
): ViewModel() {

    private val _onboardingDisplayed: MutableLiveData<Flow<Boolean>> = MutableLiveData()
    val onboardingDisplayedUseCase = _onboardingDisplayed

    fun saveOnboardingDisplayed() {
        viewModelScope.launch(Dispatchers.IO) {
            saveOnboardingDisplayedUseCase.invoke()
        }
    }

    fun getOnboardingDisplayed() {
        viewModelScope.launch(Dispatchers.IO) {
            _onboardingDisplayed.value = getOnboardingDisplayedUseCase.invoke()
        }
    }
}