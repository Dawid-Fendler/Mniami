package pl.domain.usecase

import pl.domain.repository.AuthRepository
import pl.networking.util.Resource
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Resource<Unit> {
        return authRepository.registration(email, password)
    }
}
