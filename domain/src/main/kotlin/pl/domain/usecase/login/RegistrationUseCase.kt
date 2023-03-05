package pl.domain.usecase.login

import pl.domain.repository.AuthRepository
import pl.domain.util.Resource
import pl.domain.util.UseCase
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<RegistrationUseCase.Input, Resource<Unit>> {

    override suspend fun execute(input: Input): Resource<Unit> =
        authRepository.registration(input.email, input.password)

    data class Input(val email: String, val password: String)
}
