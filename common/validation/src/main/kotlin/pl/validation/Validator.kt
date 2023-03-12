package pl.validation

interface Validator {
    fun validation(email: String, password: String, repeatedPassword: String): ValidationResult
    fun emailValidation(email: String): ValidationResult
    fun passwordValidation(password: String): ValidationResult
    fun repeatedPasswordValidation(password: String, repeatedPassword: String): ValidationResult
}
