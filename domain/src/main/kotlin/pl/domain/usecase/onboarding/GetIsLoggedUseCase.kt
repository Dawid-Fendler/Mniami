package pl.domain.usecase.onboarding

import kotlinx.coroutines.flow.Flow
import pl.domain.repository.OnboardingRepository
import pl.domain.util.UseCase
import javax.inject.Inject

class GetIsLoggedUseCase @Inject constructor(
    private val repository: OnboardingRepository
) : UseCase<Unit, Flow<Boolean>> {

    override suspend fun execute(input: Unit): Flow<Boolean> =
        repository.getIsLogged()
}
