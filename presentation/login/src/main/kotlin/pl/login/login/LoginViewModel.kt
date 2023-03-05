package pl.login.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.domain.usecase.login.LoginUseCase
import pl.domain.util.Resource
import pl.login.login.LoginViewState.LoginFailure
import pl.login.login.LoginViewState.LoginSuccessfully
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var isLoginSuccessfully: MutableLiveData<LoginViewState> = MutableLiveData()
        private set

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (loginUseCase.execute(LoginUseCase.Input(email, password))) {
                is Resource.Success -> isLoginSuccessfully.postValue(
                    LoginSuccessfully
                )
                is Resource.Failure -> isLoginSuccessfully.postValue(LoginFailure)
            }
        }
    }
}
