package sk.matejkosut.scratchcard.data.source.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class NetworkMonitorInterceptor @Inject constructor(
    private val liveNetworkMonitor:NetworkMonitor
): Interceptor {

    @Throws(NoNetworkException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        if (liveNetworkMonitor.isConnected()) {
            return chain.proceed(request)
        } else {

            throw NoNetworkException("Network Error")
        }

    }
}

class NoNetworkException(message:String): IOException(message)
