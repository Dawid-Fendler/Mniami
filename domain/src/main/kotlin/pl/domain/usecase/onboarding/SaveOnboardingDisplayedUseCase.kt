package pl.domain.usecase.onboarding

import pl.domain.repository.OnboardingRepository
import javax.inject.Inject

class SaveOnboardingDisplayedUseCase @Inject constructor(
    private val repository: pl.domain.repository.OnboardingRepository
) {

    suspend operator fun invoke() = repository.saveOnboardingDisplayed()
}
