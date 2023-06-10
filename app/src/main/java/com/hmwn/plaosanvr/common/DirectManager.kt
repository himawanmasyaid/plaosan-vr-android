package com.hmwn.plaosanvr.common

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

class DirectManager(val context: Context) {

    private val GOOGLE_MAPS = "com.google.android.apps.maps"

    fun isApplicationInstalled(packageName: String): Boolean {
        val pm: PackageManager = context.packageManager
        return try {
            pm.getPackageInfo(packageName, 0)
            true
        } catch (error: PackageManager.NameNotFoundException) {
            false
        } catch (error: Exception) {
            false
        }
    }

    fun gmapsOpenDetailLocation(latitude: Double, longitude: Double, label: String = "") {

            try {
                val gmapsUri = Uri.parse("geo:$latitude,$longitude?q=${label.replace(" ", "+")}")
                val intent = Intent(Intent.ACTION_VIEW, gmapsUri)
                intent.setPackage(GOOGLE_MAPS)
                context.startActivity(intent)
            } catch (error: Exception) {
                context.toast("Gagal : ${error.message}")
            }
    }

}