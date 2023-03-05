package pl.domain.usecase.login

import pl.domain.repository.AuthRepository
import pl.domain.util.Resource
import pl.domain.util.UseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<LoginUseCase.Input, Resource<Unit>> {

    override suspend fun execute(input: Input): Resource<Unit> =
        authRepository.login(input.email, input.password)

    data class Input(val email: String, val password: String)
}
