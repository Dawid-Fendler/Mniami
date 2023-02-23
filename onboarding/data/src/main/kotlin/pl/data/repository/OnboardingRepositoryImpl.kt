package pl.data.repository

import pl.domain.repository.OnboardingRepository
import pl.preferences.DataStorePreferences
import pl.preferences.DataStorePreferencesConstants
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val dataStorePreferences: DataStorePreferences
) : OnboardingRepository {

    override suspend fun saveOnboardingDisplayed() {
        dataStorePreferences.putPreference(
            DataStorePreferencesConstants.ONBOARDING_DISPLAYED_KEY,
            true
        )
    }

    override suspend fun getOnboardingDisplayed() =
        dataStorePreferences.getPreference(
            DataStorePreferencesConstants.ONBOARDING_DISPLAYED_KEY,
            false
        )
}
