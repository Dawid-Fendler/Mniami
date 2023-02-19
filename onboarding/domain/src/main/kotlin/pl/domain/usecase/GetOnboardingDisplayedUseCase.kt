package pl.domain.usecase

import pl.domain.repository.OnboardingRepository
import javax.inject.Inject

class GetOnboardingDisplayedUseCase @Inject constructor(
    private val repository: OnboardingRepository
) {

    suspend operator fun invoke() = repository.getOnboardingDisplayed()
}
