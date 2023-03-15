package pl.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    suspend fun saveOnboardingDisplayed()
    suspend fun getOnboardingDisplayed(): Flow<Boolean>
    suspend fun getIsLogged(): Flow<Boolean>
}
