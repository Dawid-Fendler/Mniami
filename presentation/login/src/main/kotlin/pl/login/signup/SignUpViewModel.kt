package pl.login.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.domain.usecase.login.RegistrationUseCase
import pl.domain.util.Resource
import pl.login.signup.SignUpViewState.RegistrationFailure
import pl.login.signup.SignUpViewState.RegistrationSuccessfully
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase
) : ViewModel() {

    var isRegistrationSuccessfully: MutableLiveData<SignUpViewState> = MutableLiveData()
        private set

    fun registration(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (registrationUseCase.execute(RegistrationUseCase.Input(email, password))) {
                is Resource.Success -> isRegistrationSuccessfully.postValue(RegistrationSuccessfully)
                is Resource.Failure -> isRegistrationSuccessfully.postValue(RegistrationFailure)
            }
        }
    }
}
