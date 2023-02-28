package pl.domain.usecase

import pl.domain.repository.AuthRepository
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String) {
        authRepository.registration(email, password)
    }
}