package pl.networking.model

sealed class NetworkResult<T : Any> {
    class Success<T : Any>(val data: T?) : NetworkResult<T>()
    class Failure<T : Any>(val e: Exception, val message: String?) :
        NetworkResult<T>()
}
