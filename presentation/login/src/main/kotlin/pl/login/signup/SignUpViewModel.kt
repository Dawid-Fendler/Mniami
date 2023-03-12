package pl.login.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.domain.usecase.login.RegistrationUseCase
import pl.domain.usecase.login.RegistrationUseCase.AuthResult.Exception
import pl.domain.usecase.login.RegistrationUseCase.AuthResult.Success
import pl.domain.usecase.login.RegistrationUseCase.AuthResult.ValidationError
import pl.domain.usecase.login.RegistrationUseCase.Input
import pl.login.signup.SignUpViewState.RegistrationFailure
import pl.login.signup.SignUpViewState.RegistrationSuccessfully
import pl.login.signup.SignUpViewState.RegistrationValidationError
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase
) : ViewModel() {

    var isRegistrationSuccessfully: MutableLiveData<SignUpViewState> = MutableLiveData()
        private set

    fun registration(email: String, password: String, repeatedPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = registrationUseCase.execute(Input(email, password, repeatedPassword))) {
                is Success -> isRegistrationSuccessfully.postValue(RegistrationSuccessfully)
                is Exception -> isRegistrationSuccessfully.postValue(RegistrationFailure)
                is ValidationError -> isRegistrationSuccessfully.postValue(
                    RegistrationValidationError(result.message)
                )
            }
        }
    }
}
