package pl.domain.usecase.login

import com.google.firebase.auth.AuthCredential
import pl.domain.repository.AuthRepository
import pl.domain.util.UseCase
import javax.inject.Inject

class GoogleLoginUseCase @Inject constructor(
    private val auth: AuthRepository
) : UseCase<AuthCredential, Unit> {

    override suspend fun execute(input: AuthCredential) {
        auth.googleLogin(input)
    }
}
