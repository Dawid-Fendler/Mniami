package pl.domain.usecase.login

import pl.domain.repository.AuthRepository
import pl.domain.util.Resource
import pl.domain.util.UseCase
import pl.validation.Validator
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val validator: Validator
) : UseCase<RegistrationUseCase.Input, RegistrationUseCase.AuthResult> {

    override suspend fun execute(input: Input): AuthResult {
        val validation = validator.validation(
            email = input.email,
            password = input.password,
            repeatedPassword = input.repeatedPassword
        )
        if (!validation.successful) {
            return AuthResult.ValidationError(validation.message.orEmpty())
        }
        return when (authRepository.registration(input.email, input.password)) {
            is Resource.Success -> AuthResult.Success
            is Resource.Failure -> AuthResult.Exception
        }
    }

    data class Input(val email: String, val password: String, val repeatedPassword: String)

    sealed class AuthResult {
        object Success : AuthResult()
        data class ValidationError(val message: String) : AuthResult()
        object Exception : AuthResult()
    }
}
