package pl.presentation.login

sealed class LoginViewState {
    object LoginSuccessfully : LoginViewState()
    object LoginFailure : LoginViewState()
}
