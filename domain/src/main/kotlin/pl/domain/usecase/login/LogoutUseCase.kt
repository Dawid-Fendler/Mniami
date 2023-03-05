package pl.domain.usecase.login

import pl.domain.repository.AuthRepository
import pl.domain.util.UseCase
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val auth: AuthRepository
) : UseCase<Unit, Unit> {

    override suspend fun execute(input: Unit) {
        auth.logout()
    }
}
