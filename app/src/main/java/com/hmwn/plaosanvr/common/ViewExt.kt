package com.hmwn.plaosanvr.common

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hmwn.plaosanvr.R

fun ImageView.loadRounded(context: Context, icon: Int) {
    Glide
        .with(context)
        .load(icon)
        .centerCrop()
        .placeholder(R.drawable.rounded_grey)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(16)))
        .into(this)
}

