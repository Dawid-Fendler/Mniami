package pl.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.domain.usecase.onboarding.GetOnboardingDisplayedUseCase
import pl.domain.usecase.onboarding.SaveOnboardingDisplayedUseCase
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getOnboardingDisplayedUseCase: GetOnboardingDisplayedUseCase,
    private val saveOnboardingDisplayedUseCase: SaveOnboardingDisplayedUseCase
) : ViewModel() {

    private val _onboardingDisplayed: MutableLiveData<Boolean> = MutableLiveData()
    val onboardingDisplayed = _onboardingDisplayed

    fun saveOnboardingDisplayed() {
        viewModelScope.launch(Dispatchers.IO) {
            saveOnboardingDisplayedUseCase.invoke()
        }
    }

    fun getOnboardingDisplayed() {
        viewModelScope.launch(Dispatchers.IO) {
            getOnboardingDisplayedUseCase.execute(Unit).collect {
                _onboardingDisplayed.postValue(it)
            }
        }
    }
}
