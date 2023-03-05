package pl.domain.util

interface UseCase<IN : Any, OUT : Any> {

    suspend fun execute(input: IN): OUT
}
