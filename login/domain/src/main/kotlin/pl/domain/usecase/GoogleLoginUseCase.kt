package pl.domain.usecase

import com.google.firebase.auth.AuthCredential
import pl.domain.repository.AuthRepository
import javax.inject.Inject

class GoogleLoginUseCase @Inject constructor(
    private val auth: AuthRepository
) {

    suspend operator fun invoke(credential: AuthCredential) {
        auth.googleLogin(credential)
    }
}
