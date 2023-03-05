package pl.networking.extensions

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import pl.networking.model.NetworkResult
import timber.log.Timber
import kotlin.coroutines.resumeWithException

@ExperimentalCoroutinesApi
suspend fun <T> Task<T>.await(): T {
    return suspendCancellableCoroutine { cont ->
        addOnCompleteListener {
            if (it.exception != null) {
                cont.resumeWithException(it.exception!!)
            } else {
                cont.resume(it.result, null)
            }
        }
    }
}

@SuppressWarnings("TooGenericExceptionCaught")
inline fun <T : Any> callWithHandler(apiCall: () -> T) = try {
    NetworkResult.Success(apiCall.invoke())
} catch (e: Exception) {
    Timber.e(e)
    NetworkResult.Failure(e, e.message)
}
