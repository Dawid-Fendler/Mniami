package pl.data

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pl.domain.repository.AuthRepository
import pl.networking.callback.await
import pl.networking.util.Resource
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {

    @SuppressWarnings("TooGenericExceptionCaught")
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun login(email: String, password: String): pl.networking.util.Resource<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e)
            Resource.Failure(e)
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
            Resource.Failure(e)
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
