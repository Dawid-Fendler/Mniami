package pl.domain.usecase.onboarding

import pl.domain.repository.OnboardingRepository
import pl.domain.util.UseCase
import javax.inject.Inject

class SaveOnboardingDisplayedUseCase @Inject constructor(
    private val repository: OnboardingRepository
) : UseCase<Unit, Unit> {

    override suspend fun execute(input: Unit) =
        repository.saveOnboardingDisplayed()
}
