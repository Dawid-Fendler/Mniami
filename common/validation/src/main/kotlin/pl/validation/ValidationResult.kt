package pl.validation

data class ValidationResult(
    val successful: Boolean,
    val message: String? = null
)
