package pl.presentation.signup

sealed class SignUpViewState {
    object RegistrationSuccessfully : SignUpViewState()
    object RegistrationFailure : SignUpViewState()
}
