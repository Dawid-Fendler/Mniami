package pl.domain.usecase.login

import pl.domain.repository.AuthRepository
import pl.domain.util.UseCase
import javax.inject.Inject

class SaveIsLoggedUseCase @Inject constructor(
    private val repository: AuthRepository
) : UseCase<Unit, Unit> {

    override suspend fun execute(input: Unit) =
        repository.saveIsLogged()
}
