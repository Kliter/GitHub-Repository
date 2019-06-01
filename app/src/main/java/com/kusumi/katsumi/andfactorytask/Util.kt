package com.kusumi.katsumi.andfactorytask

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun isConnected(context: Context): Boolean {
    val cm: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo: NetworkInfo = cm.activeNetworkInfo
    return networkInfo.isConnected
}