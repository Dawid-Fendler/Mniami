package pl.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pl.domain.repository.AuthRepository
import pl.domain.util.Resource
import pl.networking.extensions.await
import pl.preferences.DataStorePreferences
import pl.preferences.DataStorePreferencesConstants
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val dataStorePreferences: DataStorePreferences
) : AuthRepository {

    @SuppressWarnings("TooGenericExceptionCaught")
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun login(email: String, password: String): Resource<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e)
            Resource.Failure(e.message.orEmpty())
        }
    }

    @SuppressWarnings("TooGenericExceptionCaught")
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun registration(
        email: String,
        password: String
    ): Resource<Unit> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e)
            Resource.Failure(e.message.orEmpty())
        }
    }

    override suspend fun googleLogin(credential: AuthCredential) {
        // TODO Add handle for google Login
        auth.signInWithCredential(credential)
    }

    override suspend fun saveIsLogged() {
        dataStorePreferences.putPreference(
            DataStorePreferencesConstants.IS_LOGGED_KEY,
            true
        )
    }

    override suspend fun logout() {
        auth.signOut()
    }
}
