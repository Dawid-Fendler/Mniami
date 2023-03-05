package pl.networking.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import pl.networking.util.Constants.API_KEY

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val httpUrl = chain
            .request()
            .url
            .newBuilder()
            .addQueryParameter("apiKey", API_KEY)
            .build()
        val request = chain
            .request()
            .newBuilder()
            .url(httpUrl)
            .build()

        return chain.proceed(request)
    }
}
