package pl.validation

import android.util.Patterns
import javax.inject.Inject

class ValidatorImpl @Inject constructor() : Validator {

    override fun validation(
        email: String,
        password: String,
        repeatedPassword: String
    ): ValidationResult {
        val emailValidation = emailValidation(email)
        val passwordValidation = passwordValidation(password)
        val repeatedPasswordValidation = repeatedPasswordValidation(password, repeatedPassword)
        return when {
            !emailValidation.successful -> ValidationResult(
                successful = false,
                message = emailValidation.message
            )
            !passwordValidation.successful -> ValidationResult(
                successful = false,
                message = passwordValidation.message
            )
            !repeatedPasswordValidation.successful -> ValidationResult(
                successful = false,
                message = repeatedPasswordValidation.message
            )
            else -> ValidationResult(successful = true)
        }
    }

    override fun emailValidation(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                message = "The email can't be blank"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                message = "That's not a valid email"
            )
        }
        return ValidationResult(successful = true)
    }

    override fun passwordValidation(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                message = "The password needs to consist of at least 8 characters"
            )
        }
        val containsLetterAndDigits =
            password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!containsLetterAndDigits) {
            return ValidationResult(
                successful = false,
                message = "The password needs to contain at least one letter and digit"
            )
        }
        if (!password.any { it.isUpperCase() }) {
            return ValidationResult(
                successful = false,
                message = "The password needs to contain at upper case letter"
            )
        }
        return ValidationResult(successful = true)
    }

    override fun repeatedPasswordValidation(
        password: String,
        repeatedPassword: String
    ): ValidationResult {
        if (password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                message = "The passwords don't match"
            )
        }
        return ValidationResult(successful = true)
    }
}
