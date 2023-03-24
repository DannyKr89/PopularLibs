package ru.dk.popularlibs.domain.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkRequest
import android.widget.Toast
import io.reactivex.rxjava3.subjects.BehaviorSubject

class NetworkStatus(
    private val context: Context,
    private val status: BehaviorSubject<Boolean> = BehaviorSubject.create<Boolean>()
) : INetworkStatus {

    init {
        status.onNext(false)
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, object : NetworkCallback() {
            override fun onAvailable(network: Network) {
                status.onNext(true)

            }

            override fun onUnavailable() {
                status.onNext(false)
                Toast.makeText(context, "NO INTERNET", Toast.LENGTH_SHORT).show()
            }

            override fun onLost(network: Network) {
                status.onNext(false)
                Toast.makeText(context, "INTERNET IS LOST", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun isOnline() = status
    override fun isOnlineSingle() = status.first(false)

}