package pl.login.login

sealed class LoginViewState {
    object LoginSuccessfully : LoginViewState()
    object LoginFailure : LoginViewState()
}
