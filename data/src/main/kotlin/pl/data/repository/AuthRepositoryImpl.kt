package pl.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pl.domain.repository.AuthRepository
import pl.networking.extensions.await
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {

    @SuppressWarnings("TooGenericExceptionCaught")
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun login(email: String, password: String): pl.domain.util.Resource<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            pl.domain.util.Resource.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e)
            pl.domain.util.Resource.Failure(e.message.orEmpty())
        }
    }

    @SuppressWarnings("TooGenericExceptionCaught")
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun registration(
        email: String,
        password: String
    ): pl.domain.util.Resource<Unit> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            pl.domain.util.Resource.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e)
            pl.domain.util.Resource.Failure(e.message.orEmpty())
        }
    }

    override suspend fun googleLogin(credential: AuthCredential) {
        // TODO Add handle for google Login
        auth.signInWithCredential(credential)
    }

    override suspend fun logout() {
        auth.signOut()
    }
}
