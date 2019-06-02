package com.kusumi.katsumi.andfactorytask

import android.content.Context
import android.net.ConnectivityManager

fun isConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val info = cm.activeNetworkInfo
    return info?.isConnected ?: false
}