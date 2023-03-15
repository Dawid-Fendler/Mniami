package pl.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey

object DataStorePreferencesConstants {
    val ONBOARDING_DISPLAYED_KEY = booleanPreferencesKey("onboarding_displayed")
    val IS_LOGGED_KEY = booleanPreferencesKey("is_logged")
}
