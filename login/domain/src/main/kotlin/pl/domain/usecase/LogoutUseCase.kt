package pl.domain.usecase

import pl.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val auth: AuthRepository
) {

    suspend operator fun invoke() {
        auth.logout()
    }
}