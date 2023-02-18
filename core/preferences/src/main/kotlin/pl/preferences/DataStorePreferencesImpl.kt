package pl.preferences

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore by preferencesDataStore(
    name = "user_preferences"
)

class DataStorePreferencesImpl(
    context: Context
) : DataStorePreferences {

    private val dataStore = context.dataStore

    override suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val result = preferences[key] ?: defaultValue
            result
        }

    override suspend fun <T> putPreference(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
        dataStore.edit { preferences ->
            preferences.remove(key)
        }
    }

    override suspend fun clearAllPreference() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
