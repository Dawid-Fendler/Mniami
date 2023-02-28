package pl.domain.repository

import com.google.firebase.auth.AuthCredential

interface AuthRepository {
    suspend fun login(email: String, password: String)
    suspend fun registration(email: String, password: String)
    suspend fun googleLogin(credential: AuthCredential)
    suspend fun logout()
}