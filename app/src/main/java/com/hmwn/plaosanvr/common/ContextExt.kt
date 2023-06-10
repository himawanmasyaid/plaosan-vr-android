package com.hmwn.plaosanvr.common

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String, duration : Int = Toast.LENGTH_SHORT): Toast = Toast
    .makeText(this, message, duration)
    .apply {
        show()
    }