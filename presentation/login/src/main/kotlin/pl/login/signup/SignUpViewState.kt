package pl.login.signup

sealed class SignUpViewState {
    object RegistrationSuccessfully : SignUpViewState()
    object RegistrationFailure : SignUpViewState()
}
