package pl.domain.repository

import com.google.firebase.auth.AuthCredential
import pl.domain.util.Resource

interface AuthRepository {
    suspend fun login(email: String, password: String): Resource<Unit>
    suspend fun registration(email: String, password: String): Resource<Unit>
    suspend fun googleLogin(credential: AuthCredential)
    suspend fun saveIsLogged()
    suspend fun logout()
}
