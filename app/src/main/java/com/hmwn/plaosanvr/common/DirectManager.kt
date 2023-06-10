package com.hmwn.plaosanvr.common

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

class DirectManager(val context: Context) {

    private val GOOGLE_MAPS = "com.google.android.apps.maps"

    fun gmapsDirectionToLocation(latitude: Double, longitude: Double) {
        if (isApplicationInstalled(GOOGLE_MAPS)) {

            try {
                val gmapsUri = Uri.parse("google.navigation:q=$latitude, $longitude")
                val intent = Intent(Intent.ACTION_VIEW, gmapsUri)
                intent.setPackage(GOOGLE_MAPS)
                context.startActivity(intent)
            } catch (error: Exception) {
                context.toast("Failed")
            }

        }
    }

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

}