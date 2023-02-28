package pl.data

import android.util.Log
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import pl.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {

    override suspend fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {

            }.addOnCanceledListener {

            }
    }

    override suspend fun registration(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Log.d("Testowo", "${it.credential}")
            }.addOnFailureListener {
                Log.d("Testowo", "${it.message}")
            }
    }

    override suspend fun googleLogin(credential: AuthCredential) {
        auth.signInWithCredential(credential)
            .addOnSuccessListener { }
            .addOnFailureListener { }
    }

    override suspend fun logout() {
        auth.signOut()
    }
}