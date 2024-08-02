package sk.matejkosut.scratchcard.data.source.network

import android.content.Context
import android.net.ConnectivityManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LiveNetworkMonitor @Inject constructor(
    @ApplicationContext private val context: Context
):NetworkMonitor {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun isConnected(): Boolean {
        val network =connectivityManager.activeNetwork
        return network != null
    }
}