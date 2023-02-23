package pl.domain.usecase

import pl.domain.repository.OnboardingRepository
import javax.inject.Inject

class SaveOnboardingDisplayedUseCase @Inject constructor(
    private val repository: OnboardingRepository
) {

    suspend operator fun invoke() = repository.saveOnboardingDisplayed()
}
