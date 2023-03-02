package pl.domain.usecase

import pl.networking.util.Resource
import pl.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Resource<Unit> {
        return authRepository.login(email, password)
    }
}
